import React from "react";
import { Table } from "react-bootstrap-icons";

const AdminPost = () => {
    return (
    <>
        <Table striped bordered hover>
        <thead>
            <tr>
                <th>Tiêu đề</th>
                <th>Ngày cập nhật</th>
                <th>Hệ đào tạo</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>
            <tr>
                <td>2</td>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
            </tr>
            <tr>
                <td>3</td>
                <td colSpan={2}>Larry the Bird</td>
                <td>@twitter</td>
            </tr>
        </tbody>
        </Table>    
    </>
    );
};

export default AdminPost;
