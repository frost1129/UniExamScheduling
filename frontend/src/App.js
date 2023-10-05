import React, { createContext, useReducer } from "react";
import { Route, Routes } from "react-router-dom";
import MyUserReducer from "./reducer/MyUserReducer";
import Home from "./layouts/Home";
import cookie from "react-cookies";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Login from "./layouts/Login";

export const MyUserContext = createContext();

const App = () => {
    const [user, dispatch] = useReducer(
        MyUserReducer,
        cookie.load("user") || null
    );

    return (
        <MyUserContext.Provider value={[user, dispatch]}>
            <>
                <Header/>

                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                </Routes>
                
                <Footer/>
            </>
        </MyUserContext.Provider>
    );
};

export default App;
