import React, { useContext, useEffect, useRef, useState } from "react";
import { Alert, Button, Container, Form, InputGroup } from "react-bootstrap";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";
import { useNavigate, useParams } from "react-router-dom";
import Api, { authApi, endpoints } from "../config/Api";
import MySpinner from "../components/MySpinner";
import { MyUserContext } from "../App";

const PostConfig = () => {
    const avatar = useRef();
    const nav = useNavigate();
    const { postId } = useParams();
    const [user, ] = useContext(MyUserContext);

    const [loading, setLoading] = useState(false);
    const [post, setPost] = useState(null);

    const [notify, setNotify] = useState({
        variant: "",
        content: "",
    });

    useEffect(() => {
        const loadPostDetai = async () => {
            let { data } = await Api.get(endpoints["post-detail"](postId));
            setPost(data);
        };

        loadPostDetai();
    }, [postId]);

    const modules = {
        toolbar: [
            [{ header: [1, 2, 3, 4, 5, 6, false] }],
            ["bold", "italic", "underline", "strike", "clean"],
            [{ color: [] }, { background: [] }],
            [{ script: "sub" }, { script: "super" }],
            ["blockquote", "code-block", "image"],
            [{ indent: "-1" }, { indent: "+1" }, { align: [] }],
        ],
    };

    const loadNotify = (variant, content) => {
        setNotify({
            variant: variant,
            content: content,
        });
    };

    const sendPost = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let form = new FormData();

            form.append("id", post.id);
            form.append("title", post.title);
            form.append("content", post.content);
            form.append("adminId", user)

            if (!avatar)
                form.append("imageFile", avatar.current.files[0]);

            setLoading(true);

            let res = await authApi().put(endpoints["update-post"](postId), form);
            if (res.status === 204) {
                nav("/");
            } else
                loadNotify(
                    "danger",
                    "Hệ thống đang gặp lỗi, vui lòng thử lại sau."
                );
        };

        if (post.title === "" || post.content === "")
            loadNotify(
                "warning",
                "Vui lòng nhập đủ các thông tin của bài đăng!"
            );
        else process();
    };

    const change = (evt, field) => {
        setPost({ ...post, [field]: evt.target.value });
    };

    const handleContentChange = (content) => {
        setPost((prevPost) => ({
            ...prevPost,
            content: content,
        }));
    };

    const handleBtnDelete = () => {
        const confirmDelete = window.confirm("Bạn có muốn xóa bài viết này không?");

        const process = async () => {
            let res = await Api.delete(endpoints["delete-post"](postId));
            if (res.status === 204) {
                nav("/");
            } else
                loadNotify(
                    "danger",
                    "Hệ thống đang gặp lỗi, vui lòng thử lại sau."
                );
        };

        if (confirmDelete)
            process();
    }

    if (post === null) return <MySpinner />;

    return (
        <div className="d-flex justify-content-center m-4">
            <Form className="col-xl-10 col-9 p-2" onSubmit={sendPost}>
                <div className="mb-4 text-primary-emphasis">
                    <h3>Chi tiết bài đăng</h3>
                </div>

                {notify.variant !== "" && (
                    <Alert variant={notify.variant}>{notify.content}</Alert>
                )}

                <Form.Group className="mb-2">
                    <Form.Label>Tiêu đề bài đăng</Form.Label>
                    <Form.Control
                        value={post.title}
                        onChange={(e) => change(e, "title")}
                        type="text"
                        placeholder="Nhập tiêu đề bài viết ở đây"
                        required
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Ảnh bìa bài đăng</Form.Label>
                    <Form.Control type="file" ref={avatar} />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Nội dung bài đăng</Form.Label>

                    <ReactQuill
                        modules={modules}
                        theme="snow"
                        placeholder="Nội dung bài đăng"
                        value={post.content}
                        onChange={handleContentChange}
                    />
                </Form.Group>

                <InputGroup className="mb-3">
                    {loading === true ? (
                        <MySpinner />
                    ) : (
                        <Container className="m-auto d-flex justify-content-center ">
                            <Button className="btn-lg btn-primary mx-2 fs-6" type="submit">
                                Lưu bài đăng
                            </Button>
                            <Button 
                                type="button"
                                className="btn-lg btn-danger mx-2 fs-6"
                                onClick={handleBtnDelete}
                            >
                                Xóa bài đăng
                            </Button>
                        </Container>
                    )}
                </InputGroup>
            </Form>
        </div>
    );
};

export default PostConfig;
