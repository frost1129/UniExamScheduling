import React, { useEffect, useState } from "react";
import WeekCalendar from "../components/WeekCalendar";
import { Container, Form } from "react-bootstrap";
import MySpinner from "../components/MySpinner";
import Api, { authApi, endpoints } from "../config/Api";
import { convertYearCode } from "../config/TimeStamp";

const Exams = () => {

    const [events, setEvents] = useState([
        { name: "GLAW1315-BT2102", date: "10/10", session: "1" },
        { name: "BIOT2201-BT2102", date: "15/10", session: "1" },
        { name: "POLI1205-BT2101", date: "17/10", session: "2" },
        // Thêm các sự kiện khác theo cùng định dạng
    ]);

    
    const [semesters, setSemesters] = useState(null);
    const [exams, setExams] = useState(null);
    const [curSe, setCurSe] = useState();
    const [minDate, setMinDate] = useState(null);
    const [maxDate, setMaxDate] = useState(null);


    useEffect(() => {
        const loadSemesters = async () => {
            try {
                let { data } = await Api.get(endpoints["exam-years"]);
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
            const loadExams = async () => {
                try {
                    let { data } = await authApi().get(endpoints["year-exams"](curSe));
                    setExams(data);
                } catch (error) {
                    console.error("Error loading courses:", error);
                }
            };

            const loadTime = async () => {
                try {
                    let maxD = await Api.get(endpoints["max-date"](201));
                    let minD = await Api.get(endpoints["min-date"](201));
                    
                    setMinDate(minD.data);
                    setMaxDate(maxD.data);
                } catch (error) {
                    console.error(error);
                }
            }

            loadExams();
            loadTime();
        }
    }, [curSe]);

    if (semesters === null || exams === null || minDate === null || maxDate === null) return <MySpinner />;
    console.info(exams);

    return (
        <Container className="section col-md-10 mx-auto my-3 bg-white">

            <Form.Group className="mb-2 ">
                <h3 className="text-center text-primary-emphasis text-uppercase ">Lịch thi học kỳ </h3>
                <Form.Select className="w-50 text-center mx-auto" >
                    {semesters.map((se, index) => (
                        <option key={index} value={se}>
                            {convertYearCode(se)}
                        </option>
                    ))}
                </Form.Select>
            </Form.Group>

            <WeekCalendar startDate={new Date(minDate)} endDate={new Date(maxDate)} events={exams} kw="exam" />
        </Container>
    );
};

export default Exams;
