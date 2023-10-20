import React, { useEffect, useState } from "react";
import WeekCalendar from "../components/WeekCalendar";
import { Alert, Container, Form } from "react-bootstrap";
import MySpinner from "../components/MySpinner";
import Api, { authApi, endpoints } from "../config/Api";
import { convertYearCode } from "../config/TimeStamp";

const Schedules = () => {
    const [semesters, setSemesters] = useState(null);
    const [courses, setCourses] = useState(null);
    const [curSe, setCurSe] = useState();

    const calculateEndDate = (startDate) => {
        const endDate = new Date(startDate);
        endDate.setDate(endDate.getDate() + 7 * 9); // Mỗi kỳ học kéo dài 10 tuần
        return endDate;
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
        loadSemesters();
    }, []);

    useEffect(() => {
        if (curSe) {
            const loadCourses = async () => {
                try {
                    const response = await authApi().get(
                        endpoints["user-schedules"](curSe)
                    );
                    const data = response.data;
                    setCourses(data);
                } catch (error) {
                    console.error("Error loading courses:", error);
                }
            };

            loadCourses();
            // console.log(courses);
        }
    }, [curSe]);

    if (semesters === null || courses === null) return <MySpinner />;

    return (
        <Container className="section col-md-10 mx-auto my-3 bg-white">
            <Form.Group className="mb-2 ">
                <h3 className="text-center text-primary-emphasis ">
                    THỜI KHÓA BIỂU
                </h3>

                <Form.Select
                    className="w-50 text-center mx-auto my-2"
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

            {courses.length === 0 ? (
                <Alert variant="warning">
                    Hiện chưa có thời khóa biểu cho học kỳ này
                </Alert>
            ) : (
                <WeekCalendar
                    startDate={new Date(courses[0].dateStart)}
                    endDate={
                        new Date(
                            calculateEndDate(new Date(courses[0].dateStart))
                        )
                    }
                    events={courses}
                    kw="schedule"
                />
            )}
        </Container>
    );
};

export default Schedules;
