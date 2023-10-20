import React, { useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import Api, { endpoints } from "../config/Api";

const AddUser = ({ faculties, onClose }) => {

    const [notify, setNotify] = useState({
        variant: "",
        content: "",
    });
    const [u, setU] = useState({
        firstName: "",
        lastName: "",
        email: "", 
        gender: "", 
        role: "", 
        faculty: ""
    });

    const loadNotify = (variant, content) => {
        setNotify({
            variant: variant,
            content: content,
        });
    };

    const change = (evt, field) => {
        setU((current) => {
            return { ...current, [field]: evt.target.value };
        });
    };

    const addUser = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let form = new FormData();

            for (let field in u)
                form.append(field, u[field]);

            let res = await Api.post(endpoints['new-user'], form);
            if (res.status === 201) {
                console.log("success");
            }
            console.info(u);
        }
        process();
    };

    return (
        <Modal
            show="true"
            size="md"
            aria-labelledby="contained-modal-title-vcenter"
            backdrop="static"
            onHide={onClose}
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Thông tin tài khoản
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="info mb-4">
                    <Form onSubmit={addUser}>
                        <Form.Group className="mb-2">
                            <Form.Label>Địa chỉ Email</Form.Label>
                            <Form.Control
                                type="email"
                                value={u.email}
                                onChange={(e) => change(e, "email")}
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Họ và tên đệm</Form.Label>
                            <Form.Control
                                type="text"
                                value={u.lastName}
                                onChange={(e) => change(e, "lastName")}
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Tên</Form.Label>
                            <Form.Control
                                type="text"
                                onChange={(e) => change(e, "firstName")}
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Giới tính</Form.Label>
                            <Form.Select
                                value={u.gender}
                                onChange={(e) => change(e, "gender")}
                                required
                            >
                                <option selected><i>Giới tính</i></option>
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Vai trò</Form.Label>
                            <Form.Select 
                                value={u.role}
                                onChange={(e) => change(e, "role")}
                                required
                            >
                                <option selected><i>Chọn vai trò</i></option>
                                <option value="ROLE_ADMIN">Admin</option>
                                <option value="ROLE_STUDENT">Sinh viên</option>
                                <option value="ROLE_TEACHER">Giảng viên</option>
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Khoa trực thuộc</Form.Label>
                            <Form.Select 
                                value={u.faculty}
                                onChange={(e) => change(e, "faculty")}
                                required
                            >
                                <option selected><i>Chọn khoa</i></option>
                                {faculties.map(f => <option value={f.id}>{f.name}</option>)}
                            </Form.Select>
                        </Form.Group>
                    </Form>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={addUser}>
                    Lưu người dùng
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default AddUser;
