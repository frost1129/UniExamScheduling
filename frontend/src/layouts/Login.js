import React, { useContext, useState } from "react";
import { MyUserContext } from "../App";
import { Navigate } from "react-router-dom";
import { Alert, Button, Container, Form, Image, InputGroup, Row } from "react-bootstrap";
import ImgLogin from "../images/login_img.png";
import "./login.css";
import cookie from "react-cookies";
import Api, { authApi, endpoints } from "../config/Api";

const Login = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [notify, setNotify] = useState({
        variant: "",
        content: "",
    });
    const [u, setU] = useState({
        id: "",
        password: "",
        // type: "",
    });

    const [userType, setUserType] = useState([
        { id: "STUDENT", text: "Sinh viên"},
        { id: "TEACHER", text: "Giảng viên"},
        { id: "ADMIN", text: "Cán bộ (Admin)"}
    ]);

    const loadNotify = (variant, content) => {
        setNotify({
            variant: variant,
            content: content,
        });
    };

    const login = (evt) => {
        evt.preventDefault();

        const process = async () => {
            try {
                let res = await Api.post(endpoints["login"], {
                    id: u.id,
                    password: u.password,
                });
                cookie.save("token", res.data);

                let { data } = await authApi().get(endpoints["current-user"]);
                cookie.save("user", data);
                console.log(data);

                dispatch({
                    type: "login",
                    payload: data,
                });
            } catch (ex) {
                console.error(ex);
                loadNotify("danger", "Tên tài khoản hoặc mật khẩu không đúng");
            }
        };
        if (u.email === "") loadNotify("warning", "Vui lòng nhập ID");
        else if (u.password === "")
            loadNotify("warning", "Vui lòng nhập mật khẩu");
        else process();
    };

    const change = (evt, field) => {
        setU((current) => {
            return { ...current, [field]: evt.target.value };
        });
    };

    if (user !== null) return <Navigate to="/" />;

    return (
        <Container className="d-flex justify-content-center align-items-center p-5 ">
            <Row className="rounded-4 p-3 bg-white shadow box-area">
                <div className="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
                    <div className="featured-image mb-3">
                        <Image src={ImgLogin} fluid />
                    </div>
                </div>

                <Container className="col-md-6 rounded-4 bg-info-subtle right-box">
                    <Form onSubmit={login}>
                        <div className="mb-4">
                            <h3 className="fw-bold">ĐĂNG NHẬP</h3>
                        </div>

                        {notify.variant === "" ? (
                            ""
                        ) : (
                            <Alert variant={notify.variant}>
                                {notify.content}
                            </Alert>
                        )}

                        {/* <Form.Group className="mb-3">
                            <Form.Select 
                                name="userType" 
                                value={u.type} 
                                onChange={(e) => change(e, "type")} 
                                required
                            >
                                <option disabled selected><i>Vui lòng chọn tư cách người dùng</i></option>
                                {userType.map(type => 
                                    <option value={type.id}>{type.text}</option>
                                )}
                            </Form.Select>
                        </Form.Group> */}

                        <InputGroup className="mb-3 text-primary-emphasis">
                            <input
                                type="text"
                                onChange={(e) => change(e, "id")}
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Tài khoản"
                                name="email"
                            />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <input
                                type="password"
                                onChange={(e) => change(e, "password")}
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Mật khẩu"
                                name="password"
                            />
                        </InputGroup>

                        <InputGroup className="mb-3">
                            <Button
                                type="submit"
                                className="btn-lg btn-primary w-100 fs-6"
                            >
                                Đăng nhập
                            </Button>
                        </InputGroup>
                    </Form>
                </Container>
            </Row>
        </Container>
    );
};

export default Login;
