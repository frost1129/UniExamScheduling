import React, { useEffect, useState } from "react";
import WeekCalendar from "../components/WeekCalendar";
import { Container, Form } from "react-bootstrap";
import MySpinner from "../components/MySpinner";
import Api, { authApi, endpoints } from "../config/Api";
import { convertYearCode } from "../config/TimeStamp";

const Exams = () => {
    
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
            console.log(curSe);
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
                    let maxD = await Api.get(endpoints["max-date"](curSe));
                    let minD = await Api.get(endpoints["min-date"](curSe));
                    
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

    return (
        <Container className="section col-md-10 mx-auto my-3 bg-white">

            <Form.Group className="mb-2 ">
                <h3 className="text-center text-primary-emphasis text-uppercase ">Lịch thi học kỳ </h3>
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

            <WeekCalendar startDate={new Date(minDate)} endDate={new Date(maxDate)} events={exams} kw="exam" />
        </Container>
    );
};

export default Exams;
