import React, { useState } from "react";
import { Button, Table } from "react-bootstrap";
import ExamSlot from "./ExamSlot";
import { CaretLeftFill, CaretRightFill } from "react-bootstrap-icons";
// import "./weekcalendar.css";

const WeekCalendar = () => {
    
    const [month, setMonth] = useState(10);
    const [year, setYear] = useState(2023);

    const [events, setEvents] = useState([
        { name: 'BIOT2404-BT2101', date: '12/10', session: '7' },
        { name: 'BIOT2404-BT2101', date: '12/10', session: '8' },
        { name: 'BIOT2404-BT2101', date: '12/10', session: '9' },
        { name: 'BIOT2404-BT2101', date: '12/10', session: '10' },
        { name: 'GLAW1315-BT2102', date: '10/10', session: '1' },
        { name: 'GLAW1315-BT2102', date: '10/10', session: '2' },
        { name: 'GLAW1315-BT2102', date: '10/10', session: '3' },
        { name: 'GLAW1315-BT2102', date: '10/10', session: '4' },
        { name: 'BIOT2201-BT2102', date: '15/10', session: '1' },
        { name: 'BIOT2201-BT2102', date: '15/10', session: '2' },
        { name: 'BIOT2201-BT2102', date: '15/10', session: '3' },
        { name: 'BIOT2201-BT2102', date: '15/10', session: '4' },
        { name: 'POLI1205-BT2101', date: '17/10', session: '1' },
        { name: 'POLI1205-BT2101', date: '17/10', session: '2' },
        // Thêm các sự kiện khác theo cùng định dạng
    ]);

    // const sessionsList = ['Ca 1', 'Ca 2', 'Ca 3', 'Ca 4', 'Ca 5'];
    // const housrsList = ['7:00', '9:30', '13:00', '15:30', '18:00'];

    const sessionsList = ['Tiết 1', 'Tiết 2', 'Tiết 3', 'Tiết 4', 'Tiết 5', 
                            'Tiết 6', 'Tiết 7', 'Tiết 8', 'Tiết 9', 'Tiết 10', 
                            'Tiết 11', 'Tiết 12', 'Tiết 13', 'Tiết 14', 'Tiết 15'];
    const housrsList = ['Tiết 1', 'Tiết 2', 'Tiết 3', 'Tiết 4', 'Tiết 5', 
                            'Tiết 6', 'Tiết 7', 'Tiết 8', 'Tiết 9', 'Tiết 10', 
                            'Tiết 11', 'Tiết 12', 'Tiết 13', 'Tiết 14', 'Tiết 15'];

    const weekday = ['Thứ Hai', 'Thứ Ba', 'Thứ Tư', 'Thứ Năm', 'Thứ Sáu', 'Thứ Bảy', 'Chủ Nhật'];
    const weeks = [
        ['09/10', '10/10', '11/10', '12/10', '13/10', '14/10', '15/10'], 
        ['16/10', '17/10', '18/10', '19/10', '20/10', '21/10', '22/10'], 
        // Thêm danh sách ngày cho tuần thứ 2 ở đây
    ];

    const [currentWeekIndex, setCurrentWeekIndex] = useState(0);

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

    return (
        <div className="calendar">
            {/* Calendar Tools */}
            <div className="calendar-tools">
                {/* Calendar navigation buttons */}
                <div className="d-flex flex-column justify-content-center align-items-center">
                    {/* <h4>Từ ngày 10/10/2023 đến ngày 17/10/2023 </h4> */}

                    {weeks.length > 1 && (
                        <div className="my-2 me-2 my-lg-0 d-flex justify-content-center">
                            {currentWeekIndex !== 0 && (
                                <button onClick={handlePrevWeek} className="btn btn-link text-dark">
                                    <CaretLeftFill/>
                                </button>
                            )}

                            {currentWeekIndex !== weeks.length - 1 && (
                                <button onClick={handleNextWeek} className="btn btn-link text-dark">
                                    <CaretRightFill/>
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
                                <div className="day-field">{weeks[currentWeekIndex][index]}</div>
                            </th>
                        ))}

                        <th></th>
                    </tr>
                </thead>
                {/* Table Body */}
                <tbody className="text-center ">
                    {sessionsList.map((session, index) => (
                        <tr key={index}>
                            <td>{session}</td>

                            
                            {weeks[currentWeekIndex].map((day, dayIndex) => {
                                const matchingEvents = events.filter(
                                    event => event.date === day
                                        && parseInt(event.session) === parseInt(index) + 1
                                );

                                return (
                                    <td key={dayIndex}>
                                    {matchingEvents.length > 0 ? (
                                        <div className="event-cell">
                                        {matchingEvents.map((event, index) => (
                                            // Sử dụng component ExamSlot thay vì <div>
                                            <ExamSlot key={index} event={event} />
                                        ))}
                                        </div>
                                    ) : null}
                                    </td>
                                );
                            })}
                            

                            <td>{housrsList[index]}</td>
                        </tr>
                    ))}

                </tbody>
            </Table>
        </div>
    );
};

export default WeekCalendar;
