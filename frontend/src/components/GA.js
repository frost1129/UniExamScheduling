import React from "react";
import { Button, Form, Modal } from "react-bootstrap";

const GA = () => {
    return (
        <Modal
            show="true"
            size="md"
            aria-labelledby="contained-modal-title-vcenter"
            backdrop="static"
            centered
        >
            <Modal.Header>
                <Modal.Title id="contained-modal-title-vcenter">
                    Sắp xếp lịch thi học kỳ
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="info mb-4">
                    <Form>
                        <Form.Group className="mb-2">
                            <Form.Label>Học kỳ</Form.Label>
                            <Form.Select >
                                <option value="201">Học kỳ 1, Năm 2020 - 2021</option>
                                <option value="202">Học kỳ 2, Năm 2020 - 2021</option>
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Ngày bắt đầu kỳ thi</Form.Label>
                            <Form.Control type="date" />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Số ngày của kỳ thi</Form.Label>
                            <Form.Control
                                type="number"
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Kích thước quần thể GA</Form.Label>
                            <Form.Control
                                type="number"
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Tỉ lệ đột biến</Form.Label>
                            <Form.Control
                                type="number"
                            />
                        </Form.Group>
                    </Form>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="danger" >
                    Sắp xếp lịch thi
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default GA;
