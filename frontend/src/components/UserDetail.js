import React, { useContext } from "react";
import { Button, Form, Image, Modal } from "react-bootstrap";
import { MyUserContext } from "../App";
import MySpinner from "./MySpinner";

const UserDetail = ({ onClose }) => {
    const [user] = useContext(MyUserContext);

    if (user === null) return <MySpinner/>;

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
                <div className="avatar mb-4">
                    <div className="d-flex justify-content-between align-items-end">
                        <h6>Ảnh đại diện</h6>
                        {/* <Button variant="outline-primary">Chỉnh sửa</Button> */}
                    </div>
                    <div className="text-center">
                        <Image
                            className="img-thumbnail img-avatar"
                            src={user.image}
                            style={{ width: "50vw", maxWidth: "160px" }}
                        />
                    </div>
                </div>
                <div className="info mb-4">
                    <Form>
                        <div className="d-flex justify-content-between align-items-end">
                            <h6>Thông tin cá nhân</h6>
                        </div>
                        <Form.Group className="mb-2">
                            <Form.Label>Địa chỉ Email</Form.Label>
                            <Form.Control
                                type="email"
                                value={user.email}
                                readOnly
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Họ và tên đệm</Form.Label>
                            <Form.Control
                                type="text"
                                value={user.lastName}
                                readOnly
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Tên</Form.Label>
                            <Form.Control
                                type="text"
                                value={user.firstName}
                                readOnly
                            />
                        </Form.Group>
                    </Form>
                </div>

                {/* <div className="password">
                    <div className="d-flex justify-content-between align-items-end">
                        <h6>Đổi mật khẩu</h6>
                        <Button variant="outline-warning">
                            Xác nhận
                        </Button>
                    </div>
                    <div>
                        <Form>
                            <Form.Group className="mb-2">
                                <Form.Label>Nhập lại khẩu cũ</Form.Label>
                                <Form.Control type="password" placeholder="Nhập lại mật khẩu cũ"/>
                            </Form.Group>
                            <Form.Group className="mb-2">
                                <Form.Label>Mật khẩu mới</Form.Label>
                                <Form.Control type="password" placeholder="Nhập mật khẩu mới"/>
                            </Form.Group>
                        </Form>
                    </div>
                </div> */}
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={onClose}>
                    Đóng
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default UserDetail;
