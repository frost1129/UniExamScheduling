import React, { useEffect, useState } from "react";
import { Alert, Button, Container, Form, InputGroup, Pagination } from "react-bootstrap";
import { useNavigate, useSearchParams } from "react-router-dom";
import MySpinner from "../components/MySpinner";
import SearchPostItem from "../components/SearchPostItem";
import { Search } from "react-bootstrap-icons";
import Api, { endpoints } from "../config/Api";
import { formatTimestamp } from "../config/TimeStamp";

const AllPosts = () => {
    const [posts, setPosts] = useState(null);
 
    const [counter, setCounter] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [q, setQ] = useSearchParams();
    const [kw, setKw] = useState("");
    const nav = useNavigate();

    let items = [];
    for (let number = 1; number <= counter; number++) {
        items.push(
            <Pagination.Item 
                key={number} 
                active={number === currentPage}
                onClick={() => handlePageClick(number)}
            >
                {number}
            </Pagination.Item>
        );
    }

    const handlePageClick = (pageNum) => {
        let updatedSearchParams = new URLSearchParams(q.toString());
        updatedSearchParams.set('page', pageNum);
        setQ(updatedSearchParams.toString());
        setCurrentPage(pageNum);
    }

    const search = (evt) => {
        evt.preventDefault();
        setCurrentPage(1);
        nav(`/posts/?page=${1}&kw=${kw}`);
    }

    useEffect(() => {
        const loadPosts = async () => {
            try {
                let res = await Api.get(endpoints["all-posts"]);

                const formattedData = res.data.map(post => ({
                    ...post,
                    updatedDate: formatTimestamp(post.updatedDate),
                }));

                setPosts(formattedData);
            } catch (ex) {
                console.error(ex);
            }
        }

        loadPosts();
    }, [q]);


    if (posts === null) return <MySpinner />;

    return (
        <Container className="bg-white">
            <Container className="col-md-10 mx-auto bg-white">
                <h3 className="text-uppercase text-center fw-bold p-3 text-primary">
                    Thông báo đào tạo
                </h3>
                <Form onSubmit={search}>
                    <InputGroup className="mb-3 shadow-sm">
                        <Form.Control
                            type="text"
                            value={kw}
                            name="kw"
                            onChange={e => setKw(e.target.value)}
                            placeholder="Nội dung cần tìm..."
                        />
                        <Button type="submit" variant="outline-secondary">
                            Tìm kiếm
                            <Search className="mx-1 " />
                        </Button>
                    </InputGroup>
                </Form>

                {posts.length === 0 ? <Alert variant="warning">Không có bài viết nào với từ khóa này</Alert> : 
                    <Container className="mb-3">
                        {posts.map(post => 
                            <SearchPostItem key={post.id} post={post} />
                        )}
                    </Container>
                }

                {counter === 0 ? "" : 
                    <Pagination className="justify-content-center">{items}</Pagination>
                }
            </Container>
        </Container>
    );
};

export default AllPosts;
