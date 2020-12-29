import { useState,useEffect,useRef  } from "react"
import { useHistory } from "react-router-dom";
import CustomerService from "../services/CustomerService";
import HeaderComponent from "../componenets/HeaderComponent";
import MediaService from "../services/MediaService";

const EditCustomer=()=>{
    const refImage = useRef();
    const[medias,setMedias] = useState([]);
    const history = useHistory();
    const[customer,setCustomer] = useState({id: history.location.state?.id
        ,name:'',lastName:'',address:'',phone:'',media:''});

    const{id,name,lastName,address,phone,media} = customer;

    const showImage = async (e) => {
        await setCustomer({...customer,media:medias.filter(media => media.id == refImage.current.value)[0]})
        e.preventDefault();
    }

    const changeHandler = (e)=>{
        setCustomer({...customer,[e.target.name]:e.target.value});
    }

    async function getData(){
        const res = await CustomerService.getCustomerByID(id);
        if(res.status===200){
            setCustomer({...customer,name:res.data.name,lastName:res.data.lastName,address:res.data.address,phone:res.data.phone,media:res.data.media});
        }
        MediaService.getAllMedia().then((res)=>{
            if(res.status===200){
                setMedias(res.data);
            }
        })
    }

    useEffect(()=>{
        getData();
    },[])

    const updateCustomer = async (e) =>{
        e.preventDefault();
        const res = await CustomerService.updateCustomer(customer);
        if(res.status!==200){
            window.alert("Customer eklenemedi..!")
        }
        history.push('/customers');
    }

    const cancel =(e) =>{
        history.push('/customers');
    }


    return(
        <div>
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Role</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group ">
                                        <label> Category Image </label>
                                        <div className="row">
                                            <div className="col-md-6">
                                                <select onChange={(e) => showImage(e)}
                                                        ref={refImage} className="form-control" id="option">
                                                    {
                                                        medias.map(
                                                            media =>
                                                                <option key={media.id} selected={customer.media.id == media.id}
                                                                        value={media.id}>{media.name} </option>
                                                        )
                                                    }
                                                </select>
                                            </div>
                                            <div className="col-md-4 offset-md-2">

                                                <div className="">
                                                    <img src={'data:image/png;base64,' + media.fileContent}
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
                                    <button className="btn btn-success" onClick={(e)=>updateCustomer(e)}>Save</button>
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
export default EditCustomer;