import React, { useContext, useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import { MyUserContext } from "../App";
import UserDetail from "./UserDetail";
import { Container, Nav, NavDropdown, Navbar } from "react-bootstrap";
import logo from "../images/ou_logo_long.png";

const Header = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [showUserDetail, setShowUserDetail] = useState(false);
    const location = useLocation();

    const handleShowUserDetail = () => {
        setShowUserDetail(true);
    };

    const logout = () => {
        dispatch({
            type: "logout",
        });
    };

    useEffect(() => {
        window.scrollTo(0, 0);
    }, [location]);

    // if (admissionTypes === null || topics === null) return <MySpinner />;

    return (
        <>
            <Navbar expand="lg" className="bg-body-tertiary">
                <Container>
                    <Link to="/" className="text-decoration-none">
                        <Navbar.Brand>
                            <img
                                src={logo}
                                height="60"
                                className="d-inline-block align-top"
                                alt="OU logo"
                            />
                        </Navbar.Brand>
                    </Link>

                    <span>
                        <Navbar.Collapse
                            id="basic-navbar-nav"
                            className="float-end"
                        >
                            <Nav className="me-auto">
                                <Nav.Link>
                                    <Link
                                        to="/"
                                        className="text-decoration-none text-dark"
                                    >
                                        Trang chủ
                                    </Link>
                                </Nav.Link>

                                { user === null ? "" : 
                                    user.role === "ROLE_ADMIN" ? 
                                    <>
                                    <Nav.Link>
                                        <Link
                                            to="/admin/posts"
                                            className="text-decoration-none text-dark"
                                        >
                                            Quản lý bài đăng
                                        </Link>
                                    </Nav.Link>
                                    <Nav.Link>
                                        <Link
                                            to="/admin/users"
                                            className="text-decoration-none text-dark"
                                        >
                                            Quản lý người dùng
                                        </Link>
                                    </Nav.Link>
                                    <Nav.Link>
                                        <Link
                                            to="/admin/schedules"
                                            className="text-decoration-none text-dark"
                                        >
                                            Quản lý thời khóa biểu
                                        </Link>
                                    </Nav.Link>
                                    <Nav.Link>
                                        <Link
                                            to="/admin/exams"
                                            className="text-decoration-none text-dark"
                                        >
                                            Quản lý lịch thi
                                        </Link>
                                    </Nav.Link>
                                    </>
                                    :
                                    <>
                                    <Nav.Link>
                                        <Link
                                            to="/schedules"
                                            className="text-decoration-none text-dark"
                                        >
                                            Thời khóa biểu
                                        </Link>
                                    </Nav.Link>
                                    <Nav.Link>
                                        <Link
                                            to="/exams"
                                            className="text-decoration-none text-dark"
                                        >
                                            Lịch thi
                                        </Link>
                                    </Nav.Link>
                                    </>
                                }
                                

                                {user === null ? (
                                    <Link
                                        to="/login"
                                        className="btn btn-primary text-decoration-none"
                                    >
                                        Đăng nhập
                                    </Link>
                                ) : (
                                    <NavDropdown
                                        className="fw-bold text-dark"
                                        title={`Xin chào, ${user.firstName}`}
                                        id="basic-nav-dropdown"
                                    >
                                        <NavDropdown.Item>
                                            <Link
                                                className="text-decoration-none text-dark"
                                                onClick={handleShowUserDetail}
                                            >
                                                Thông tin tài khoản
                                            </Link>
                                        </NavDropdown.Item>
                                        <NavDropdown.Item>
                                            <Link
                                                onClick={logout}
                                                className="text-decoration-none text-dark"
                                            >
                                                Đăng xuất
                                            </Link>
                                        </NavDropdown.Item>
                                    </NavDropdown>
                                )}
                            </Nav>
                        </Navbar.Collapse>
                    </span>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                </Container>
            </Navbar>

            {/* HIỂN THỊ USER DETAILS */}
            {showUserDetail && (
                <UserDetail onClose={() => setShowUserDetail(false)} />
            )}
        </>
    );
};

export default Header;
