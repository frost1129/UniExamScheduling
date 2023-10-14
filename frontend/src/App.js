import React, { createContext, useReducer } from "react";
import { Route, Routes } from "react-router-dom";
import MyUserReducer from "./reducer/MyUserReducer";
import Home from "./layouts/Home";
import cookie from "react-cookies";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Login from "./layouts/Login";
import Post from "./layouts/Post";
import PostConfig from "./admin/PostConfig";
import NewPost from "./admin/NewPost";
import AdminPost from "./admin/AdminPost";
import AdminUser from "./admin/AdminUser";

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
                    <Route path="/schedules" element={<Login />} />
                    <Route path="/exams" element={<Login />} />
                    <Route path="/posts" element={<Login />} />
                    <Route path="/posts/:postId" element={<Post/>} />
                    
                    <Route path="/admin/posts" element={<AdminPost/>} />
                    <Route path="/admin/users" element={<AdminUser/>} />
                    <Route path="/admin/schedules" element={<AdminPost/>} />
                    <Route path="/admin/exams" element={<AdminPost/>} />
                    <Route path="/admin/posts/new" element={<NewPost/>} />
                    <Route path="/admin/posts/:postId" element={<PostConfig/>} />
                </Routes>
                
                <Footer/>
            </>
        </MyUserContext.Provider>
    );
};

export default App;
