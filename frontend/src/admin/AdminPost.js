import React, { useEffect, useState } from "react";
import { Alert, Button, Container, Image, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import Api, { endpoints } from "../config/Api";
import { formatTimestamp } from "../config/TimeStamp";
import MySpinner from "../components/MySpinner";
import { InfoCircle } from "react-bootstrap-icons";

const AdminPost = () => {
    const [posts, setPosts] = useState(null);

    useEffect(() => {
        const loadPosts = async () => {
            try {
                let res = await Api.get(endpoints["all-posts"]);

                const formattedData = res.data.map((post) => ({
                    ...post,
                    updatedDate: formatTimestamp(post.updatedDate),
                }));

                setPosts(formattedData);
            } catch (ex) {
                console.error(ex);
            }
        };

        loadPosts();
    }, []);

    if (posts === null) return <MySpinner />;

    return (
        <div className="d-flex justify-content-center">
            <Container className="col-xl-10 col-9 p-2">
                <h3>Quản lý bài đăng</h3>

                <Link
                    type="button"
                    className="mb-3 btn btn-outline-success"
                    to="/admin/posts/new"
                >
                    Thêm bài đăng
                </Link>

                {posts.length === 0 ? (
                    <Alert variant="warning">Không có bài viết nào</Alert>
                ) : (
                    <Table striped bordered hover>
                        <thead>
                            <tr className="text-center ">
                                <th>Ảnh bìa</th>
                                <th>Tiêu đề</th>
                                <th>Ngày cập nhật</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {posts.map((post) => (
                                <tr key={post.id}>
                                    <td className="text-center "><Image src={post.image} style={{ maxHeight: '120px' }} fluid rounded /></td>
                                    <td>{post.title}</td>
                                    <td>{post.updatedDate}</td>
                                    <td className="text-center ">
                                        <Link to={`/admin/posts/${post.id}`}><InfoCircle/></Link>
                                    </td>
                                    {console.log(post)}
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                )}
            </Container>
        </div>
    );
};

export default AdminPost;
