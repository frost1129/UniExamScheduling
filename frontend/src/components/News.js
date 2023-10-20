import React, { useEffect, useState } from "react";
import { Container, Image, Row } from "react-bootstrap";
import MySpinner from "./MySpinner";
import { Link } from "react-router-dom";
import "./news.css";
import Api, { endpoints } from "../config/Api";
import { formatTimestamp } from "../config/TimeStamp";
import { ClockFill } from "react-bootstrap-icons";

const News = () => {
    const [post1, setPost1] = useState([]);
    const [post1Remain, setPost1Remain] = useState([]);

    useEffect(() => {
        const loadPost1 = async () => {
            let {data} = await Api.get(endpoints["top-posts"]);
            const formattedData = data.map(post => ({
                ...post,
                updatedDate: formatTimestamp(post.updatedDate),
            }));
            setPost1(formattedData[0]);
            setPost1Remain(formattedData.slice(1));
        }

        loadPost1();
    }, []);

    if (post1 === null || post1.length === 0) 
        return <MySpinner />;

    return (
        <Container className="section col-md-10 mx-auto my-3 bg-white">
            <Row className="my-3 p-2 rounded shadow">
                <h3 className="text-sm-start text-center fw-bold">
                    <Link className="text-decoration-none link-primary ">
                        Các thông báo
                    </Link>
                </h3>

                <div key={post1.id} className="col-sm-12 col-md-7 col-xs-12 col-lg-7">
                    <div className="thumb-container">
                        <Link to={`/posts/${post1.id}`}>
                            <Image className="thumb mb-3 border-2" alt={post1.title} src={post1.image} rounded />
                        </Link>
                    </div>
                    <div>
                        <small className="text-secondary">
                            <ClockFill className="mx-2" />
                            {post1.updatedDate}
                        </small>
                    </div>
                    <Link to={`/posts/${post1.id}`} className="text-decoration-none">
                        <h3>{post1.title}</h3>
                    </Link>
                </div>

                <div className="col-sm-12 col-md-5 col-xs-12 col-lg-5">
                    {post1Remain.map(post => 
                    <Row key={post.id} className="mt-3 news-md">
                        <div className="col-4">
                            <div className="thumb-container">
                                <Link to={`/posts/${post.id}`}>
                                    <Image className="thumb img-md" alt={post.title} src={post.image} rounded />
                                </Link>
                            </div>
                        </div>
                        <div className="col-8">
                            <small className="text-secondary">
                                <ClockFill className="mx-2" />
                                {post.updatedDate}
                            </small>
                            <Link to={`/posts/${post.id}`} className="text-decoration-none">
                                <p className="mb-4 link-dark link-opacity-50-hover">
                                    {post.title}
                                </p>
                            </Link>
                        </div>
                    </Row>
                    )}

                    <Row className="my-1">
                        <Link to='/posts' className="text-decoration-none text-center text-uppercase">
                            <small>Xem tất cả</small>
                        </Link>
                    </Row>
                </div>
            </Row>


        </Container>
    );
};

export default News;
