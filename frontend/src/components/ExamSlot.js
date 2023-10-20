import React from "react";

const ExamSlot = ({ event, kw }) => {
    return (
        <>
            {kw === "schedule" && (
                <div className="exam-slot bg-info-subtle rounded-2 m-1">
                    {event.scheduleId}
                    <div/>
                    Giảng viên: {event.teacher.lastName} {event.teacher.firstName}
                </div>
            ) }

            {kw === "exam" && (
                <div className="exam-slot bg-info-subtle rounded-2 m-1">
                    {event.courseSchedule.scheduleId}
                </div>
            )}
        
        </>
    );
};

export default ExamSlot;
