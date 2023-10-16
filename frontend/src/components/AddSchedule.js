import React from "react";
import { Button, Form, Modal } from "react-bootstrap";

const AddSchedule = () => {
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
                    Thêm lịch học
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
                            <Form.Label>Ngày bắt đầu học kỳ</Form.Label>
                            <Form.Control type="date" />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>File dữ liệu lịch học (định dạng csv)</Form.Label>
                            <Form.Control type="file" />
                        </Form.Group>
                    </Form>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" >
                    Tải lên
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default AddSchedule;
