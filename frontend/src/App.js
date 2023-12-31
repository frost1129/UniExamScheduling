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
import Schedules from "./layouts/Schedules";
import Exams from "./layouts/Exams";
import AllPosts from "./layouts/AllPosts";
import AdminSchedules from "./admin/AdminSchedules";
import AdminExams from "./admin/AdminExams";

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
                    <Route path="/schedules" element={<Schedules />} />
                    <Route path="/exams" element={<Exams />} />
                    <Route path="/posts" element={<AllPosts />} />
                    <Route path="/posts/:postId" element={<Post/>} />
                    
                    <Route path="/admin/posts" element={<AdminPost/>} />
                    <Route path="/admin/users" element={<AdminUser/>} />
                    <Route path="/admin/schedules" element={<AdminSchedules/>} />
                    <Route path="/admin/exams" element={<AdminExams/>} />
                    {/* <Route path="/admin/exams/preview" element={<PreviewExams/>} /> */}
                    <Route path="/admin/posts/new" element={<NewPost/>} />
                    <Route path="/admin/posts/:postId" element={<PostConfig/>} />
                </Routes>
                
                <Footer/>
            </>
        </MyUserContext.Provider>
    );
};

export default App;
