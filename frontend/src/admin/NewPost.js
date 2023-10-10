import React, { useRef, useState } from "react";
import Api, { endpoints } from "../config/Api";
import { useNavigate } from "react-router-dom";
import { Alert, Button, Form, InputGroup } from "react-bootstrap";
import ReactQuill from "react-quill";
import MySpinner from "../components/MySpinner";
import "react-quill/dist/quill.snow.css";

const NewPost = () => {
    const avatar = useRef();
    const nav = useNavigate();
    const [loading, setLoading] = useState(false);

    const [notify, setNotify] = useState({
        variant: "",
        content: "",
    });

    const [post, setPost] = useState({
        "title": "", 
        "image": "",
        "content": "",  
    });

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

            for (let field in post)
                form.append(field, post[field]);

            if (post.image === "")
                form.append("imageFile", avatar.current.files[0]);

            setLoading(true)

            let res = await Api.post(endpoints['new-post'], form);
            if (res.status === 201) {
                nav("/");
            } else
                loadNotify("danger", "Hệ thống đang gặp lỗi, vui lòng thử lại sau.");
        }

        if (post.title === "" || post.content === "")
            loadNotify("warning", "Vui lòng nhập đủ các thông tin của bài đăng!");
        else if (avatar.current === undefined && post.image === "")
            loadNotify("warning", "Vui lòng chọn ảnh bìa cho bài đăng");
        else 
            process();
    };

    const change = (evt, field) => {
        setPost({...post, [field]: evt.target.value});
    };

    const handleContentChange = (content) => {
        setPost(prevPost => ({
          ...prevPost,
          content: content
        }));
      };

    return (
        <div className="d-flex justify-content-center m-4">
            <Form className="col-xl-10 col-9 p-2" onSubmit={sendPost}>
                <div className="mb-4 text-primary-emphasis">
                    <h3>Bài đăng mới</h3>
                </div>

                {notify.variant !== "" &&
                    <Alert variant={notify.variant}>
                        {notify.content}
                    </Alert>
                }

                <Form.Group className="mb-2">
                    <Form.Label>Tiêu đề bài đăng</Form.Label>
                    <Form.Control onChange={(e) => change(e, "title")} type="text" placeholder="Nhập tiêu đề bài viết ở đây" required/>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Ảnh bìa bài đăng</Form.Label>
                    <Form.Control type="file" ref={avatar}  />
                </Form.Group>

                <Form.Group className="mb-2">
                    <Form.Label>Đường dẫn của ảnh bìa (nếu có)</Form.Label>
                    <Form.Control onChange={(e) => change(e, "image")} type="text" placeholder="Đường dẫn của ảnh bìa"/>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Nội dung bài đăng</Form.Label>
                    
                    <ReactQuill
                        modules={modules}
                        theme="snow"
                        placeholder="Nội dung bài đăng"
                        value={ post.content }
                        onChange={handleContentChange}
                    />
                </Form.Group>

                <InputGroup className="mb-3">
                    {loading === true ? 
                    <MySpinner /> : 
                    <Button className="btn-lg btn-primary mx-auto fs-6" type="submit">
                        Đăng bài
                    </Button>
                    }   
                </InputGroup>
            </Form>
        </div>
    );
};

export default NewPost;
