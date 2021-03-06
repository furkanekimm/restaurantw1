import {  useState,useRef,useEffect } from "react"
import { useHistory } from "react-router-dom";
import CustomerService from "../services/CustomerService";
import HeaderComponent from "../componenets/HeaderComponent";
import MediaService from "../services/MediaService";

const AddCustomer = (props)=>{
    const refImage = useRef();
    const history = useHistory();
    const[medias,setMedias]=useState([]);
    const[customer,setCustomer] = useState({name:'',lastName:'',address:'',phone:'',media:''});
    const{name,lastName,address,phone,media} = customer;

    const changeHandler = (e)=>{
        setCustomer({...customer,[e.target.name]:e.target.value});
    }

    const showImage = async (e) => {
        const refId= refImage.current.value;
        e.preventDefault();
        await setCustomer({...customer,media:medias.filter(media => media.id == refId)[0]})
    }

    const saveCustomer = async (e) =>{
        e.preventDefault();
        const res = await CustomerService.addCustomer(customer);
        if(res.status!==200){
            window.alert("Customer eklenemedi..!")
        }
        history.push('/customers');
    }

    async function getMedias(){
        const res = await MediaService.getAllMedia();
        if(res.status===200){
            setMedias(res.data);
            setCustomer({...customer,media:res.data[0]})
        }
    }
    useEffect(()=>{
        getMedias();
    },[])

    const cancel =(e) =>{
        history.push('/select');
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
                                    <div className="form-group ">
                                        <label> Customer Image </label>
                                        <div className="row">
                                            <div className="col-md-6">
                                                <select onChange={(e) => showImage(e)}
                                                        className="form-control" id="option" ref={refImage}>
                                                    {
                                                        medias.map(
                                                            media =>
                                                                <option key={media.id}
                                                                        value={media.id}>{media.name} </option>
                                                        )
                                                    }
                                                </select>
                                            </div>
                                            <div className="col-md-4 offset-md-2">
                                                <div className="">
                                                    <img src={'data:image/png;base64,' + customer.media.fileContent}
                                                         width="45rem" height="39rem"
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label> Name </label>
                                        <input placeholder="Customer Name" name="name" className="form-control"
                                               value={name} onChange={(e)=>changeHandler(e)}/>
                                    </div>
                                    <div className="form-group">
                                        <label> LastName </label>
                                        <input placeholder="LastName" name="lastName" className="form-control"
                                               value={lastName} onChange={(e)=>changeHandler(e)}/>
                                    </div>
                                    <div className="form-group">
                                        <label> Address </label>
                                        <input placeholder="Address" name="address" className="form-control" type="textarea"
                                               value={address} onChange={(e)=>changeHandler(e)}/>
                                    </div>
                                    <div className="form-group">
                                        <label> Phone </label>
                                        <input placeholder="Phone" name="phone" className="form-control" type="text"
                                               value={phone} onChange={(e)=>changeHandler(e)}/>
                                    </div>
                                    <button className="btn btn-success" onClick={(e)=>saveCustomer(e)}>Save</button>
                                    <button className="btn btn-danger" onClick={(e)=>cancel(e)}
                                            style={{marginLeft: "10px"}}>Cancel
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
export default AddCustomer;