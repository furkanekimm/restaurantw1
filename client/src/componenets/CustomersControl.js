import HeaderComponent from "./HeaderComponent";
import {useHistory} from 'react-router-dom';
const CustomersControl = (props)=>{
    const history = useHistory();
    const cancel = (e)=>{
        history.push('/homepagee')
    }
    return(
        <div>
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row mt-5">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Customer</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <div className="card-body " >
                                            <button className="btn btn-info col-md-12" style={{height:"5rem"}} onClick={(e)=>history.push('/add-customer')}>Add New Customer</button>
                                        </div>

                                    </div>
                                    <div className="form-group">
                                        <div className="card-body">
                                            <button className="btn btn-info col-md-12" style={{height:"5rem"}} onClick={(e)=>history.push('/customers')}>List Customers</button>
                                        </div>
                                    </div>

                                    <button className="btn btn-danger col-md-12" style={{height:"5rem"}} onClick={(e)=>cancel(e)}
                                            >Cancel
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default CustomersControl;