import React, { useEffect, useState } from "react";
import Api, { endpoints } from "../config/Api";
import MySpinner from "../components/MySpinner";
import { Alert, Button, Col, Container, Form, Row, Table } from "react-bootstrap";
import { convertYearCode } from "../config/TimeStamp";

const AdminExams = () => {
    const [schedules, setSchedules] = useState(null);
    const [faculties, setFaculties] = useState(null);
    const [semesters, setSemesters] = useState(null);
    const [showAddSchedule, setShowAddSchedule] = useState(false);

    const [curFaculty, setCurFaculty] = useState(null);
    const [curSe, setCurSe] = useState(null);

    const handleShowAddSchedule = () => {
        setShowAddSchedule(true);
    };

    const handleFacultyChange = (e) => {
        const selectedFacultyId = e.target.value;
        setCurFaculty(faculties[selectedFacultyId - 1]);
    };

    const initData = () => {
        setCurFaculty(faculties[0]);
        setCurSe(semesters[0]);
    };

    useEffect(() => {
        const loadSemesters = async () => {
            try {
                let { data } = await Api.get(endpoints["schedule-years"]);
                setSemesters(data);

                if (data.length > 0) {
                    setCurSe(data[0]);
                }
            } catch (error) {
                console.error("Error loading semesters:", error);
            }
        };

        const loadFaculties = async () => {
            await loadSemesters();
            let { data } = await Api.get(endpoints["faculties"]);
            setFaculties(data);

            if (data.length > 0) {
                setCurFaculty(data[0]);
            }
        };

        loadFaculties();
    }, []);

    useEffect(() => {
        const loadSchedules = async () => {
            const params = {
                facultyId: curFaculty.id,
                yearCode: curSe,
            };
    
            try {
                const response = await Api.get(endpoints["faculty-schedules"], {
                    params: params,
                });
    
                const data = response.data;
                setSchedules(data);
            } catch (error) {
                console.error(error);
            }
        }

        if (curFaculty !== null && curSe !== null)
            loadSchedules();
    }, [curFaculty, curSe]);

    if (schedules === null || faculties === null || semesters === null) return <MySpinner/>;

    return (
        <div className="d-flex justify-content-center">
            <Container className="col-xl-10 col-9 p-2">
                <h3>Quản lý thời khóa biểu</h3>

                <Button
                    variant="outline-success"
                    type="button"
                    className="mb-3"
                    onClick={handleShowAddSchedule}
                >
                    Import thời khóa biểu
                </Button>

                <Row className="mb-3">
                    <Form.Group as={Col}>
                        <Form.Label>Khoa</Form.Label>
                        <Form.Select
                            defaultValue={curFaculty.id}
                            onChange={handleFacultyChange}
                        >
                            {faculties.map((f) => (
                                <option value={f.id}>{f.name}</option>
                            ))}
                        </Form.Select>
                    </Form.Group>

                    <Form.Group as={Col}>
                        <Form.Label>Học kỳ</Form.Label>
                        <Form.Select
                            onChange={(event) => {
                                setCurSe(event.target.value);
                            }}
                            value={curSe}
                        >
                            {semesters.map((se, index) => (
                                <option key={index} value={se}>
                                    {convertYearCode(se)}
                                </option>
                            ))}
                        </Form.Select>
                    </Form.Group>
                </Row>

                {schedules.length !== 0 ? (
                    <Table striped bordered hover>
                        <thead>
                            <tr className="text-center">
                                <th>Mã thời khóa biểu</th>
                                <th>Thứ</th>
                                <th>Tiết bắt đầu</th>
                                <th>Tên môn</th>
                                <th>Giảng viên</th>
                            </tr>
                        </thead>
                        <tbody>
                            {schedules.map((s) => (
                                <tr key={s.scheduleId}>
                                    <td className="text-center">{s.scheduleId}</td>
                                    <td className="text-center">{s.weekday}</td>
                                    <td className="text-center">{s.sessionStart}</td>
                                    <td>{s.course.name}</td>
                                    <td>{s.teacher.lastName} {s.teacher.firstName}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                ) : (
                    <Alert variant="warning">
                        Hiện chưa có thông tin lịch thi
                    </Alert>
                )}
            </Container>

            {/* {showAddSchedule && (
                <Add
                    semesters={semesters}
                    onClose={() => setShowAddSchedule(false)}
                />
            )} */}
        </div>
    );
};

export default AdminExams;
