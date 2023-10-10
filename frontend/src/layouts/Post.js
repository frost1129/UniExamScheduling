import React, { useContext, useEffect, useState } from "react";
import { MyUserContext } from "../App";
import { Link, useParams } from "react-router-dom";
import MySpinner from "../components/MySpinner";
import { Container, Image } from "react-bootstrap";
import QuillHtmlRender from "../components/QuillHtmlRender";
import { formatTimestamp } from "../config/TimeStamp";
import Api, { endpoints } from "../config/Api";

const Post = () => {
    const [post, setPost] = useState(null);
    const { postId } = useParams();

    useEffect(() => {
        const loadPostDetai = async () => {
            let { data } = await Api.get(endpoints["post-detail"](postId));
            const formattedDate = formatTimestamp(data.updatedDate);
            setPost({
                ...data,
                updatedDate: formattedDate,
            });
        };

        loadPostDetai();
    }, [postId]);

    if (post === null) return <MySpinner />;

    return (
        <Container className="col-md-10 mx-auto bg-white">
            <article>
                <Image
                    className="img-fit mt-3"
                    src={post.image}
                    fluid
                    rounded
                />

                <header className="my-4">
                    <h3 className="fw-bold text-uppercase mt-3 text-primary">
                        {post.title}
                    </h3>
                    <p className="text-muted fst-italic">
                        Được đăng vào {post.updatedDate}
                    </p>
                    {post.admissionType !== null && 
                        <p className="badge bg-secondary text-decoration-none link-light">
                            {post.admissionType.name}
                        </p>
                    }
                </header>

                <section className="my-4">
                    <QuillHtmlRender content={post.content} />
                </section>
            </article>
        </Container>
    );
};

export default Post;
