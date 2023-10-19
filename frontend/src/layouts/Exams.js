import React, { useState } from "react";
import WeekCalendar from "../components/WeekCalendar";
import { Container, Form } from "react-bootstrap";

const Exams = () => {

    const [events, setEvents] = useState([
        { name: "GLAW1315-BT2102", date: "10/10", session: "1" },
        { name: "BIOT2201-BT2102", date: "15/10", session: "1" },
        { name: "POLI1205-BT2101", date: "17/10", session: "2" },
        // Thêm các sự kiện khác theo cùng định dạng
    ]);

    return (
        <Container className="section col-md-10 mx-auto my-3 bg-white">

            <Form.Group className="mb-2 ">
                <h3 className="text-center text-primary-emphasis text-uppercase ">Lịch thi học kỳ </h3>
                <Form.Select className="w-50 text-center mx-auto" >
                    <option value="201">Học kỳ 1, Năm 2020 - 2021</option>
                    <option value="202">Học kỳ 2, Năm 2020 - 2021</option>
                </Form.Select>
            </Form.Group>

            <WeekCalendar startDate={new Date('2023-10-12')} endDate={new Date('2023-10-20')} events={events} />
        </Container>
    );
};

export default Exams;
