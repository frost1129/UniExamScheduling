import React, { useRef, useState } from "react";
import { Alert, Button, Form, Modal } from "react-bootstrap";
import MySpinner from "./MySpinner";
import Api, { endpoints } from "../config/Api";
import { useNavigate } from "react-router-dom";

const AddSchedule = ({ semesters, onClose }) => {

    const csvFile = useRef();
    const [schedule, setSchedule] = useState({
        year: "",
        date: "",
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

            form.append("file", csvFile.current.files[0]);

            setLoading(true)
            let res = await Api.post(endpoints["upload-schedules"], form);
            if (res.status === 201) {
                nav("/admin/schedules");
            } else if (res.status === 400)
                setErr("File csv không đúng định dạng, vui lòng kiểm tra lại!");
        }

        if (schedule.date.length === 0 || schedule.year.length === 0 || !csvFile.current.files[0])
            setErr("Vui lòng nhập đầy đủ các trường");
        else if (semesters.includes(parseInt(schedule.year)))
            setErr("Dữ liệu của học kỳ này đã tồn tại");
        else {
            process();
        }
    };

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
                    Thêm lịch học
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="info mb-4">
                    {err === null?"":<Alert variant="warning">{err}</Alert>}
                    <Form>
                        <Form.Group className="mb-2">
                            <Form.Label>Học kỳ</Form.Label>
                            <Form.Control
                                type="number"
                                value={schedule.year}
                                onChange={(e) => change(e, "year")}
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Ngày bắt đầu học kỳ</Form.Label>
                            <Form.Control
                                type="date"
                                value={schedule.date}
                                onChange={(e) => change(e, "date")}
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>
                                File dữ liệu lịch học (định dạng csv)
                            </Form.Label>
                            <Form.Control type="file" ref={csvFile} />
                        </Form.Group>
                    </Form>
                </div>
            </Modal.Body>
            <Modal.Footer>
                {loading === true ? (
                    <MySpinner />
                ) : (
                    <Button variant="primary" onClick={submit}>Tải lên</Button>
                )}
            </Modal.Footer>
        </Modal>
    );
};

export default AddSchedule;
