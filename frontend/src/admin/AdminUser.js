import React, { useEffect, useState } from "react";
import { Button, Col, Container, Form, Row, Table } from "react-bootstrap";
import { PencilFill, Trash2Fill } from "react-bootstrap-icons";
import MySpinner from "../components/MySpinner";
import Api, { endpoints } from "../config/Api";
import AddUser from "../components/AddUser";
import AddSchedule from "../components/AddSchedule";
import AddRegister from "../components/AddRegister";
import GA from "../components/GA";

const AdminUser = () => {
    const [users, setUsers] = useState(null);
    const [faculties, setFaculties] = useState(null);
    const [showAddUser, setShowAddUser] = useState(false);
    const [roles, ] = useState([
        // { id: "ROLE_ADMIN", title: "Admin" },
        { id: "ROLE_STUDENT", title: "Sinh viên" },
        { id: "ROLE_TEACHER", title: "Giảng viên" },
    ]);

    const [curFaculty, setCurFaculty] = useState(null);
    const [curRole, setCurRole] = useState(null);

    const handleShowAddUser = () => {
        setShowAddUser(true);
    };

    useEffect(() => {
        const loadFaculties = async () => {
            let {data} = await Api.get(endpoints["faculties"]);
            setFaculties(data);
        }

        loadFaculties();
    }, []);

    useEffect(() => {
        const loadUsers = async () => {
            if (curRole !== null) {
                let res = await Api.post(endpoints["users"], {
                    "faculty": curFaculty,
                    "role": curRole.id,
                });
                setUsers(res.data);
            }
        }

        if (faculties !== null && curFaculty === null && curRole === null) {
            initData();
        }

        loadUsers();
    }, [faculties, curFaculty, curRole]);

    const initData = () => {
        setCurFaculty(faculties[0]);
        setCurRole(roles[0]);
    }

    const handleFacultyChange = (e) => {
        const selectedFacultyId = e.target.value;
        setCurFaculty(faculties[selectedFacultyId]);
    };

    const handleRoleChange = (e) => {
        const selectedRoleId = e.target.value;
        setCurRole(roles.filter(r => r.id === selectedRoleId)[0]);
    }

    if ( faculties === null || curFaculty === null || curRole === null || users === null) return <MySpinner/>;

    return (
    <div className="d-flex justify-content-center">
        <Container className="col-xl-10 col-9 p-2">
            <h3>Quản lý người dùng</h3>

            {/* <Row className="mb-3 text-start "> */}
            <Button variant="outline-success" type="button" className="mb-3" onClick={handleShowAddUser}>
                Thêm người dùng
            </Button>
            {/* </Row> */}

            <Row className="mb-3">
                <Form.Group as={Col}>
                <Form.Label>Khoa</Form.Label>
                <Form.Select defaultValue={curFaculty.id} onChange={handleFacultyChange}>
                    {faculties.map(f => <option value={f.id}>{f.name}</option>)}
                </Form.Select>
                </Form.Group>

                <Form.Group as={Col}>
                <Form.Label>Vai trò</Form.Label>
                <Form.Select defaultValue={curRole.id} onChange={handleRoleChange}>
                    {roles.map(r => <option value={r.id}>{r.title}</option>)}
                </Form.Select>
                </Form.Group>

                {/* <InputGroup as={Col} className="mb-3">
                    <InputGroup.Text id="basic-addon1"><Search/></InputGroup.Text>
                    <Form.Control
                        placeholder="Tìm kiếm người dùng"
                    />
                </InputGroup> */}
            </Row>

            <Table striped bordered hover>
            <thead>
                <tr className="text-center">
                    <th>Id</th>
                    <th>Họ và tên đệm</th>
                    <th>Tên</th>
                    <th>Giới tính</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                { users.map(user => 
                <tr>
                    <td>{user.id}</td>
                    <td>{user.lastName}</td>
                    <td>{user.firstName}</td>
                    <td>{user.gender}</td>
                    <td className="text-center "><PencilFill/> <Trash2Fill/></td>
                </tr>
                    )}
            </tbody>
            </Table>  
        </Container>  
        
        {showAddUser && (
            <AddUser faculties={faculties} onClose={() => setShowAddUser(false)}/>
        )}
    </div>
    );
};

export default AdminUser;
