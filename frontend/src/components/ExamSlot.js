import React from "react";

const ExamSlot = ({ event }) => {
    return (
        <div className="exam-slot bg-info-subtle rounded-2 m-1">
            {event.name} - {event.date}
        </div>
    );
};

export default ExamSlot;
