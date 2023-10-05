import cookie from "react-cookies";

const MyUserReducer = (currrentState, action) => {
    switch (action.type) {
        case "login":
            return action.payload;
        case "logout":
            cookie.remove("token");
            cookie.remove("user");
            return null;
        case "update_user":
            return action.payload;
    }
    return currrentState;
};

export default MyUserReducer;
