import React from "react";

const ExamSlot = ({ event }) => {
    return (
        <div className="exam-slot bg-info-subtle rounded-2 m-1">
            {event.scheduleId}
            <div/>
            Giảng viên: {event.teacher.lastName} {event.teacher.firstName}
        </div>
    );
};

export default ExamSlot;
