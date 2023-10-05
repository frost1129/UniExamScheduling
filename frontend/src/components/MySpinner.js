import React from "react";
import { Container, Spinner } from "react-bootstrap";

const MySpinner = () => {
    return (
        <Container className="text-center my-2">
            <Spinner animation="border" variant="info" />
            <h3>Loading...</h3>
        </Container>
    );
};

export default MySpinner;
