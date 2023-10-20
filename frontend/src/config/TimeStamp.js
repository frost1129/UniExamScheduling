export const formatTimestamp = (timestamp) => {
    const date = new Date(timestamp);
    const hours = date.getHours().toString().padStart(2, "0");
    const minutes = date.getMinutes().toString().padStart(2, "0");
    const seconds = date.getSeconds().toString().padStart(2, "0");
    const day = date.getDate().toString().padStart(2, "0");
    const month = (date.getMonth() + 1).toString().padStart(2, "0");
    const year = date.getFullYear();

    return `${hours}:${minutes}:${seconds} ${day}/${month}/${year}`;
};

export const getDate = (timestamp) => {
    const date = new Date(timestamp);
    const day = date.getDate().toString().padStart(2, "0");
    const month = (date.getMonth() + 1).toString().padStart(2, "0");

    return `${day}/${month}`;
};

export const getYear = (timestamp) => {
    const date = new Date(timestamp);
    const year = date.getFullYear();

    return `${year}`;
};

export const getDMY = (timestamp) => {
    const date = new Date(timestamp);
    const day = date.getDate().toString().padStart(2, "0");
    const month = (date.getMonth() + 1).toString().padStart(2, "0");
    const year = date.getFullYear();

    return `${day}/${month}/${year}`;
};

export const calculateWeek = (startDate, endDate) => {
    const dateList = [];
    const currentDate = new Date(getMondayOfWeek(startDate));
    const finalDate = new Date(getSundayOfWeek(endDate));

    while (currentDate <= finalDate) {
        const formattedDate = getDate(currentDate);
        dateList.push(formattedDate);

        // Tăng ngày hiện tại lên 1 để kiểm tra ngày tiếp theo
        currentDate.setDate(currentDate.getDate() + 1);
    }

    return dateList;
};

export const getMondayOfWeek = (selectedDate) => {
    const date = new Date(selectedDate);
    const dayOfWeek = date.getDay(); // Ngày trong tuần (0 là Chủ Nhật, 1 là Thứ 2, 2 là Thứ 3, ..., 6 là Thứ 7)

    // Tính khoảng cách để lùi lại ngày đến Thứ 2 (1 ngày)
    const daysToSubtract = dayOfWeek === 0 ? 6 : dayOfWeek - 1;

    // Lùi ngày về Thứ 2
    date.setDate(date.getDate() - daysToSubtract);

    return date;
};

const getSundayOfWeek = (inputDate) => {
    const date = new Date(inputDate);
    const dayOfWeek = date.getDay();
    const sundayDate = new Date(date);

    // Chuyển ngày hiện tại đến Chủ Nhật của tuần
    sundayDate.setDate(date.getDate() - dayOfWeek + 7);

    return sundayDate;
};

const convertYearCode = (yearCode) => {
    if (typeof(yearCode) !== 'number')
        return "Input không hợp lệ";

    const year = Math.floor(yearCode/10);
    const semester = yearCode % 10;

    return `Học kỳ ${semester}, Năm 20${year} - 20${year+1}`;
}
