import React, { useEffect, useState } from "react";
import { Alert, Button, Table } from "react-bootstrap";
import ExamSlot from "./ExamSlot";
import { CaretLeftFill, CaretRightFill } from "react-bootstrap-icons";
import MySpinner from "./MySpinner";
import { calculateWeek, getDMY } from "../config/TimeStamp";
// import "./weekcalendar.css";

const WeekCalendar = ({ startDate, endDate, events, kw }) => {
    const [weeks, setWeeks] = useState(null);

    const sessionsList = ["Ca 1", "Ca 2", "Ca 3", "Ca 4", "Ca 5"];
    const housrsList = ["7:00", "9:30", "13:00", "15:30", "18:00"];
    const weekday = [
        "Thứ Hai",
        "Thứ Ba",
        "Thứ Tư",
        "Thứ Năm",
        "Thứ Sáu",
        "Thứ Bảy",
        "Chủ Nhật",
    ];
    const [currentWeekIndex, setCurrentWeekIndex] = useState(0);

    useEffect(() => {
        const weekDates = calculateWeek(startDate, endDate);

        const newWeeks = [];
        for (let i = 0; i < weekDates.length; i += 7) {
            newWeeks.push(weekDates.slice(i, i + 7));
        }
        setWeeks(newWeeks);
    }, [startDate, endDate]);

    const convertToDayOfWeek = (number) => {
        // Chuyển số từ 2 đến 8 thành ngày trong tuần (0: Chủ Nhật, 1: Thứ Hai, ..., 6: Thứ Bảy)
        return (number === 8) ? 0 : number - 1;
    }

    const handlePrevWeek = () => {
        if (currentWeekIndex > 0) {
            setCurrentWeekIndex(currentWeekIndex - 1);
        }
    };

    const handleNextWeek = () => {
        if (currentWeekIndex < weeks.length - 1) {
            setCurrentWeekIndex(currentWeekIndex + 1);
        }
    };

    if (weeks === null || events === null) return <MySpinner />;

    return (
        <div className="calendar">
            {/* Calendar Tools */}
            <div className="calendar-tools">
                {/* Calendar navigation buttons */}
                <div className="d-flex flex-column justify-content-center align-items-center">
                    {weeks.length > 1 && (
                        <div className="my-2 me-2 my-lg-0 d-flex justify-content-center">
                            {currentWeekIndex !== 0 && (
                                <button
                                    onClick={handlePrevWeek}
                                    className="btn btn-link text-dark"
                                >
                                    <CaretLeftFill />
                                </button>
                            )}

                            {currentWeekIndex !== weeks.length - 1 && (
                                <button
                                    onClick={handleNextWeek}
                                    className="btn btn-link text-dark"
                                >
                                    <CaretRightFill />
                                </button>
                            )}
                        </div>
                    )}
                </div>
            </div>

            {/* Calendar Table */}
            <Table className="week" bordered>
                {/* Table Headers */}
                <thead>
                    <tr className="text-center">
                        <th></th>

                        {weekday.map((dayInfo, index) => (
                            <th key={index}>
                                <div className="weekday-field">{dayInfo}</div>
                                <div className="day-field">
                                    {weeks[currentWeekIndex][index]}
                                </div>
                            </th>
                        ))}

                        <th></th>
                    </tr>
                </thead>
                {/* Table Body */}
                {kw === "schedule" && (
                    <tbody className="text-center ">
                        {sessionsList.map((session, index) => (
                            <tr key={index}>
                                <td>{session}</td>

                                {weeks[currentWeekIndex].map(
                                    (day, dayIndex) => {
                                        const matchingEvents = events.filter((event) => {
                                            const eventStartDate = new Date(event.startDate);
                                            const eventStartDayOfWeek = convertToDayOfWeek(event.weekday) // 0: Chủ Nhật, 1: Thứ Hai, ..., 6: Thứ Bảy
                                            const targetDayOfWeek = new Date(day).getDay();
                                            
                                            return (
                                                eventStartDayOfWeek === dayIndex &&
                                                parseInt(event.sessionStart) === parseInt(index) + 1
                                                // && new Date(day) < eventStartDate
                                            )
                                        });

                                        return (
                                            <td key={dayIndex}>
                                                {matchingEvents.length > 0 ? (
                                                    <div className="event-cell">
                                                        {matchingEvents.map(
                                                            (event, index) => (
                                                                // Sử dụng component ExamSlot thay vì <div>
                                                                <ExamSlot key={index} event={event} kw="schedule"/>
                                                            )
                                                        )}
                                                    </div>
                                                ) : null}
                                            </td>
                                        );
                                    }
                                )}

                                <td>{housrsList[index]}</td>
                            </tr>
                        ))}
                    </tbody>
                )}
                {kw === "exam" && (
                    <tbody className="text-center ">
                        {sessionsList.map((session, index) => (
                            <tr key={index}>
                                <td>{session}</td>

                                {weeks[currentWeekIndex].map(
                                    (day, dayIndex) => {
                                        const matchingEvents = events.filter(
                                            (event) =>
                                                getDMY(event.examDate) === day &&
                                                parseInt(event.timeSlot.id) === parseInt(index) + 1
                                        );

                                        return (
                                            <td key={dayIndex}>
                                                {matchingEvents.length > 0 ? (
                                                    <div className="event-cell">
                                                        {matchingEvents.map(
                                                            (event, index) => (
                                                                // Sử dụng component ExamSlot thay vì <div>
                                                                <ExamSlot key={index} event={event} kw="exam"/>
                                                            )
                                                        )}
                                                    </div>
                                                ) : null}
                                            </td>
                                        );
                                    }
                                )}

                                <td>{housrsList[index]}</td>
                            </tr>
                        ))}
                    </tbody>
                )}
            </Table>
        </div>
    );
};

export default WeekCalendar;
