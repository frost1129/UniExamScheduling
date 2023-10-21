import React, { useState } from "react";
import MySpinner from "../components/MySpinner";
import { Container, Modal } from "react-bootstrap";
import { convertYearCode } from "../config/TimeStamp";
import WeekCalendar from "../components/WeekCalendar";

const PreviewExams = ({ onClose }) => {
    
    // const [minDate, setMinDate] = useState(null);
    // const [maxDate, setMaxDate] = useState(null);

    // if (minDate === null || maxDate === null) return <MySpinner />;

    return (
            <Modal
            show="true"
            size="md"
            aria-labelledby="contained-modal-title-vcenter"
            backdrop="static"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Lịch sinh tự động sử dụng GA
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="info mb-4">
                    {/* {err === null ? "" : <Alert variant="warning">{err}</Alert>} */}
                    
                </div>
            </Modal.Body>
            <Modal.Footer>
                {/* {loading === true ? (
                    <MySpinner />
                ) : (
                    <Button variant="danger" onClick={submit}>
                        Lưu
                    </Button>
                )} */}
            </Modal.Footer>
        </Modal>
    );
};

export default PreviewExams;
