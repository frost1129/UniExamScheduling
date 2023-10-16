import React from "react";
import { Button, Form, Modal } from "react-bootstrap";

const AddUser = () => {
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
                    Thông tin tài khoản
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="info mb-4">
                    <Form>
                        <Form.Group className="mb-2">
                            <Form.Label>Địa chỉ Email</Form.Label>
                            <Form.Control
                                type="email"
                                // value={user.email}
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Họ và tên đệm</Form.Label>
                            <Form.Control
                                type="text"
                                // value={user.lastName}
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Tên</Form.Label>
                            <Form.Control
                                type="text"
                                // value={user.firstName}
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Giới tính</Form.Label>
                            <Form.Select >
                                <option value="male">Nam</option>
                                <option value="female">Nữ</option>
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Vai trò</Form.Label>
                            <Form.Select >
                                <option value="admin">Admin</option>
                                <option value="student">Sinh viên</option>
                                <option value="teacher">Giảng viên</option>
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Khoa trực thuộc</Form.Label>
                            <Form.Select >
                                <option value="admin">Công nghệ sinh học</option>
                                <option value="student">Công nghệ thông tin</option>
                                <option value="teacher">Đào tạo đặc biệt</option>
                            </Form.Select>
                        </Form.Group>
                    </Form>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" >
                    Lưu người dùng
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default AddUser;
