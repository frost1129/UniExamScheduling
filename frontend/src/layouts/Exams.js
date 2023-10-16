import React from "react";
import WeekCalendar from "../components/WeekCalendar";
import { Container, Form } from "react-bootstrap";

const Exams = () => {
    return (
        <Container className="section col-md-10 mx-auto my-3 bg-white">

            <Form.Group className="mb-2 ">
                <h3 className="text-center text-primary-emphasis ">Lịch thi học kỳ </h3>
                <Form.Select className="w-50 text-center mx-auto" >
                    <option value="201">Học kỳ 1, Năm 2020 - 2021</option>
                    <option value="202">Học kỳ 2, Năm 2020 - 2021</option>
                </Form.Select>
            </Form.Group>

            <WeekCalendar />
        </Container>
    );
};

export default Exams;
