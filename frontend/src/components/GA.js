import React, { useState } from "react";
import { Alert, Button, Form, Modal } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import Api, { endpoints } from "../config/Api";
import { convertYearCode } from "../config/TimeStamp";
import MySpinner from "./MySpinner";

const GA = ({ semesters, onClose, setPreview }) => {

    const [schedule, setSchedule] = useState({
        date: "",
        length: "",
        size: "",
        mutationRate: "",
        yearCode: "",
    });

    const [err, setErr] = useState(null);
    const [loading, setLoading] = useState(false);
    const nav = useNavigate();

    const change = (evt, field) => {
        setSchedule((current) => { 
            return { ...current, [field]: evt.target.value };
        });
    };

    const submit = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let form = new FormData();

            for (let field in schedule)
                form.append(field, schedule[field]);

            setLoading(true)
            let res = await Api.post(endpoints["implement-GA"], form);
            if (res.status === 200) {
                nav("/admin/exams");
            } else
                setErr("Server gặp trục trặc, vui lòng thử lại sau!");
        }

        if (schedule.yearCode.length === 0 || 
            schedule.date.length === 0 || 
            schedule.length.length === 0 || 
            schedule.size.length === 0 || 
            schedule.mutationRate.length === 0)
            setErr("Vui lòng nhập đầy đủ các trường");
        else if (!semesters.includes(parseInt(schedule.yearCode)))
            setErr("Mã học kỳ không hợp lệ!");
        else {
            process();
        }
    }


    return (
        <Modal
            show="true"
            size="md"
            aria-labelledby="contained-modal-title-vcenter"
            backdrop="static"
            centered
            onHide={onClose}
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Sắp xếp lịch thi học kỳ
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="info mb-4">
                    {err === null ? "" : <Alert variant="warning">{err}</Alert>}
                    <Form>
                    <Form.Group className="mb-2">
                            <Form.Label>Học kỳ</Form.Label>
                            <Form.Select value={schedule.yearCode} onChange={(e) => change(e, "yearCode")} >
                                {semesters.map((se, index) => (
                                    <option key={index} value={se}>
                                        {convertYearCode(se)}
                                    </option>
                                ))}
                            </Form.Select>
                            {/* <Form.Control type="number" onChange={(e) => change(e, "yearCode")}/> */}
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Ngày bắt đầu kỳ thi</Form.Label>
                            <Form.Control type="date" onChange={(e) => change(e, "date")} />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Số ngày của kỳ thi</Form.Label>
                            <Form.Control type="number" onChange={(e) => change(e, "length")}/>
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Kích thước quần thể GA</Form.Label>
                            <Form.Control type="number" onChange={(e) => change(e, "size")}/>
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Tỉ lệ đột biến</Form.Label>
                            <Form.Control type="number" step="0.01" onChange={(e) => change(e, "mutationRate")}/>
                        </Form.Group>
                    </Form>
                </div>
            </Modal.Body>
            <Modal.Footer>
                {loading === true ? (
                    <MySpinner />
                ) : (
                    <Button variant="danger" onClick={submit}>
                        Tải lên
                    </Button>
                )}
            </Modal.Footer>
        </Modal>
    );
};

export default GA;
