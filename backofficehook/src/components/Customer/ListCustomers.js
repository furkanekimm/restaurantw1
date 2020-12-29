import { useContext, useEffect, useState } from "react";
import ReactExport from 'react-export-excel';
import { useHistory } from 'react-router-dom';
import HeaderComponent from "../HeaderComponent";
import { Context } from '../../contexts/Context';
import CustomerService from "../../services/CustomerService";
import { redirectWithId } from "../../routerRedirect";
import Pagination from "react-js-pagination";
const ListCustomers = (props) => {
    const ExcelFile = ReactExport.ExcelFile;
    const ExcelSheet = ReactExport.ExcelFile.ExcelSheet;
    const ExcelColumn = ReactExport.ExcelFile.ExcelColumn;
    const history = useHistory();
    const { users, authorizeControl } = useContext(Context);
    const [customers, setCustomers] = useState([]);
    const [dataExcel, setDataExcel] = useState([]);
    const [page, setPage] = useState(0);
    const [total, setTotal] = useState();
    useEffect(() => {
        handlePageChange(0);
    }, []);

    const deleteCustomer = async (id) => {
        // const res = await CustomerService.deleteCustomer(id, users);
        // if (res.status !== 200) {
        //     window.alert("Customer silinemedi...!");
        // }
        // setCustomers(customers.filter(customer => customer.id !== id))
        // window.alert(id + "id'li customer silindi..!");
    }

    const handlePageChange = async (pageNumber) => {
        const pageNum = pageNumber;
        const controlContext = await authorizeControl();
        if (await controlContext === false) {
            history.push('/');
        }
        const res = await CustomerService.customersWithPage(users, pageNum);
        console.log(res.data);
        const { data } = res;

        if (res.status !== 200 || !data) {
            window.alert("sunucudan veriler alınamadı...");
            return;
        }
        setDataExcel(data.content);
        setCustomers(data.content);
        setTotal(data.totalElements);
        setPage(pageNum);
    }

    const mapProduct = () => {

        return (customers.map(
            customer =>
                <tbody>
                    <tr key={customer.id} className="border" style={{ height: "95px" }}>
                        <td>{customer.name}</td>
                        <td>{customer.lastName}</td>
                        <td>{customer.address}</td>
                        <td>{customer.phone}</td>
                        <td><img
                            style={{ marginLeft: "3rem", marginRight: "10px" }}
                            src={'data:image/png;base64,' + customer.media.fileContent}
                            width="45rem" height="39rem"
                        /></td>
                        <td>
                            <button
                                onClick={(e) => redirectWithId("/update-customer", customer.id, history)}
                                className="btn btn-info"> Update
                        </button>
                            <button style={{ margin: "6px" }}
                                onClick={(e) => deleteCustomer(customer.id)}
                                className="btn btn-danger"> Delete
                        </button>
                        </td>
                    </tr>
                </tbody >
        ))
    }


    const getTableAndPagination = () => {

        // if (!product || !product.category) {
        //     return (
        //         <div>
        //             <label>Kayıt Bulunamadı...!</label>
        //         </div>
        //     )
        // }

        return (
            <div className="row col-md-12">
                <table className="table table-borderless  table-bordered col-md-12">
                    <thead className="table-bordered">
                        <tr>
                            <th>Name</th>
                            <th>LastName</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Image</th>
                            <th>Actions</th>
                        </tr>
                    </thead >
                    {
                        mapProduct()
                    }
                </table>
                <div className="row" style={{ marginLeft: "25rem" }}>
                    <Pagination
                        itemClass="page-item"
                        linkClass="page-link"
                        activePage={page + 1}
                        itemsCountPerPage={5}
                        totalItemsCount={total}
                        pageRangeDisplayed={5}
                        onChange={(e) => handlePageChange(e)}
                    />
                </div>
            </div>
        )
    }

    return (
        <div>
            <HeaderComponent />
            <div className="container">
                <div className="row">
                    <button style={{ marginTop: "15px", marginBottom: "10px" }} className="btn btn-primary"
                        onClick={(e) => history.push('/add-customer')}>Add
                       Customer
                    </button>
                    <div style={{ marginLeft: "1rem", marginTop: "0.9rem" }}>
                        <ExcelFile filename="customers" element={<button className="btn btn-success">Export Excel</button>} >
                            <ExcelSheet data={dataExcel} name="content">
                                <ExcelColumn label="ID" value="id" />
                                <ExcelColumn label="NAME" value="name" />
                                <ExcelColumn label="LAST NAME" value="lastName" />
                                <ExcelColumn label="ADDRESS" value="address" />
                                <ExcelColumn label="PHONE NUMBER" value="phone" />
                            </ExcelSheet>
                        </ExcelFile>
                    </div>
                </div>
                <div className="row" >
                    {
                        getTableAndPagination()
                    }
                </div>
            </div>
        </div>
    )
}
export default ListCustomers;