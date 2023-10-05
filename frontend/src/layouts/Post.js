import React, { useContext, useEffect, useState } from "react";
import { MyUserContext } from "../App";
import { Link, useParams } from "react-router-dom";
import MySpinner from "../components/MySpinner";
import { Container, Image } from "react-bootstrap";
import QuillHtmlRender from "../components/QuillHtmlRender";
import { formatTimestamp } from "../config/Timestamp";

const Post = () => {
    const [user] = useContext(MyUserContext);
    const [post, setPost] = useState(null);
    const { postId } = useParams();

    const [loading, setLoading] = useState(false);
    const [notify, setNotify] = useState({
        variant: "",
        content: "",
    });

    const loadNotify = (variant, content) => {
        setNotify({
            variant: variant,
            content: content,
        });
    };

    useEffect(() => {
        const loadPostDetai = async () => {
            let { data } = await Api.get(endpoints["post-details"](postId));
            const formattedDate = formatTimestamp(data.updatedDate);
            setPost({
                ...data,
                updatedDate: formattedDate,
            });
        };

        loadPostDetai();
    }, [postId, loading]);

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
                    <Link className="badge bg-secondary text-decoration-none link-light">
                        {post.admissionType.name}
                    </Link>
                </header>

                <section className="my-4">
                    <QuillHtmlRender content={post.content} />
                </section>
            </article>
        </Container>
    );
};

export default Post;
