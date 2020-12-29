import {  useEffect, useState } from "react";
import { useHistory } from 'react-router-dom';
import CustomerService from "../services/CustomerService";
import HeaderComponent from "../componenets/HeaderComponent";
import Pagination from "react-js-pagination";
const Customers = (props) => {
    const history = useHistory();
    const [customers, setCustomers] = useState([]);
    const [page, setPage] = useState(0);
    const [total, setTotal] = useState();
    const [searchName,setSearchName] = useState();

    useEffect(() => {
        handlePageChange(0);
    }, []);

    const selectCustomer = (id)=>{
        history.push({
            state:{
                customerId:id
            },
            pathname:'/products'
        });
    }

    const deleteCustomer = async (id) => {
        const res = await CustomerService.deleteCustomer(id);
        if (res.status !== 200) {
            window.alert("Customer silinemedi...!");
        }
        setCustomers(customers.filter(customer => customer.id !== id))
        window.alert(id+"id'li customer silindi..!");
    }

    const changeHandler=async (e)=>{
        setSearchName(e.target.value)
        if(e.target.value.length===3 || e.target.value.length===4){
            await searchWithName();
        }
        if(e.target.value===''){
            await handlePageChange();
        }

    }

    const handlePageChange = async (pageNumber) => {
        const pageNum = pageNumber ;
        const res = await CustomerService.customersWithPage(pageNum);
        const { data } = res;

        if (res.status !== 200 || !data) {
            window.alert("sunucudan veriler alınamadı...");
            return;
        }

        setCustomers(data.content);
        setTotal(data.totalElements);
        setPage(pageNum);
    }

    const searchWithName =async ()=>{

        const res = await CustomerService.getCustomerByName(searchName,0);
        if(res.status===200){
            setCustomers(res.data.content);
        }
    }

    const mapProduct = () => {

        return (customers.map(
            customer =>
                <tbody>
                <tr key={customer.id} className="border" style={{ height: "95px" }}>
                    <img
                        style={{height: "45px",marginLeft:"20px",borderRadius:"10px"}}
                        src={'data:image/png;base64,' + customer.media.fileContent}
                    />
                    <td>{customer.name}</td>
                    <td>{customer.lastName}</td>
                    <td>{customer.address}</td>
                    <td>{customer.phone}</td>
                    <td>
                        <button
                            onClick={(e) => selectCustomer(customer.id)}
                            className="btn btn-info"> Select
                        </button>
                    </td>
                </tr>
                </tbody >
        ))
    }


    const getTableAndPagination = () => {

      /*   if (!product || !product.category) {
            return (
               <div>
                    <label>Kayıt Bulunamadı...!</label>
                 </div>
            )
         }*/

        return (
            <div className="row col-md-12">
                <table className="table table-borderless  table-bordered col-md-12"  >
                    <thead className="table-bordered">
                    <tr>
                        <th>Image</th>
                        <th>Name</th>
                        <th>LastName</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Actions</th>
                    </tr>
                    </thead >
                    {
                        mapProduct()
                    }
                </table>
                <div className="row" style={{ marginLeft: "30rem" }}>
                    <Pagination
                        itemClass="page-item"
                        linkClass="page-link"
                        activePage={page+1}
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
                    <button style={{ marginTop: "15px", marginBottom: "10px" ,marginLeft:"1rem" ,width:"5rem"}} className="btn btn-danger"
                            onClick={(e) => history.push('/select')}>
                        Back
                    </button>
                    {/*<button style={{ marginTop: "15px", marginBottom: "10px" ,marginLeft:"3rem" ,width:"5rem",visible:"hidden"}} className="btn btn-success"
                            onClick={(e) => searchWithName(e)}>
                        Search
                    </button>*/}
                    <input placeholder="Search Customer" name="searchName" className="form-control" style={{height:"2.6rem",width:"20rem",marginLeft:"35.4rem",marginTop:"13px"}}
                           value={searchName} onChange={(e)=>changeHandler(e)}/>
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
export default Customers;