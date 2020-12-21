import { useEffect, useRef, useState,useContext } from "react";
import {Context} from '../../contexts/Context';
import { Redirect, useHistory } from "react-router-dom";
import MediaService from "../../services/MediaService";
import WaiterService from "../../services/WaiterService";
import HeaderComponent from '../HeaderComponent';
import Loading from "../Loading";
const EditWaiter = (props) => {
    const{users}=useContext(Context);
    const [loading, setLoading] = useState(true);
    const history = useHistory();
    const refImage = useRef();
    const [waiter, setWaiter] = useState({
        id: history.location.state?.id,
        waiterName: '',
        waiterLastName: '',
        phoneNumber: '',
        email: '',
        selectedImage: '',
    });

    const { id, waiterName, waiterLastName, phoneNumber, email, selectedImage } = waiter;

    const [media, setMedia] = useState([]);

    const updateWaiter = async (e) => {
        e.preventDefault();
        let waiter = {
            id: id,
            waiterName: waiterName,
            waiterLastName: waiterLastName,
            phoneNumber: phoneNumber,
            email: email,
            media: selectedImage
        }
        if (waiter.waiterName === '' || waiter.waiterLastName === '' || waiter.phoneNumber === '' || waiter.email === '') {
            history.push('/updatewaiter');
        } else {
            WaiterService.updateWaiter(waiter,users).then(res => {
                if (res.status == '200') {
                    console.log("Başarılı ekleme");
                    history.push("/waiters");
                }
            });
        }

    }

    async function getAllMedia() {
        const res = await MediaService.getAllMedia(users);
        if (res.status == '200') {
            setMedia(res.data);
            setWaiter({ ...waiter, selectedImage: res.data[0] })
        }
    }
    async function getWaiterByID() {
        const res = await WaiterService.getWaiterByID(id,users);
        if (res.status == '200') {
            setWaiter({
                ...waiter,
                waiterName: res.data.waiterName,
                waiterLastName: res.data.waiterLastName,
                phoneNumber: res.data.phoneNumber,
                email: res.data.email,
                selectedImage: res.data.media
            });
        }
    }
    async function getData() {
        await getAllMedia();
        await getWaiterByID();
        setLoading(false)
    }

    useEffect(() => {
        getData();
    }, [])

    const showImage = (e) => {
        setWaiter({ ...waiter, selectedImage: media.filter(media => media.id == refImage.current.value)[0] })
    }

    const changeHandler = (e) => {
        setWaiter({ ...waiter, [e.target.name]: e.target.value });
    }

    const cancel = (e) => {
        history.push('/waiters');
    }



    return (
        <div>
            {loading == true ?
                <Loading /> :
                <div className="container">
                    <HeaderComponent />
                    <div className="card  col-sm-6 offset-md-3 border-dark mt-4">
                        <div className="card-body">
                            <h4 className="card-header text-center bg-transparent">Add Waiter</h4>
                            <form>
                                <div className="form-group">
                                    <div className="form-group ">
                                        <label> Waiter Image </label>
                                        <div className="row">
                                            <div className="col-md-6">
                                                <select onChange={(e) => showImage(e)}
                                                    ref={refImage} className="form-control" id="option">
                                                    {
                                                        media.map(
                                                            media =>
                                                                <option key={media.id} selected={selectedImage.id == media.id}
                                                                    value={media.id}>{media.name} </option>
                                                        )
                                                    }
                                                </select>
                                            </div>
                                            <div className="col-md-4 offset-md-2">
                                                <div className="">
                                                    <img src={'data:image/png;base64,' + selectedImage.fileContent}
                                                        width="45rem" height="39rem"
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="input-group col-sm-12 mt-2">
                                        <div className="input-group-text col-sm-10">
                                            <label className="col-sm-5">Name</label>
                                            <input className="col-sm-7" type="text" name="waiterName" value={waiterName} onChange={(e) => changeHandler(e)} />
                                        </div>
                                        <div className="input-group-text col-sm-10">
                                            <label className="col-sm-5">LastName</label>
                                            <input className="col-sm-7" type="text" name="waiterLastName" value={waiterLastName} onChange={(e) => changeHandler(e)} />
                                        </div>
                                        <div className="input-group-text col-sm-10">
                                            <label className="col-sm-5">PhoneNumber</label>
                                            <input className="col-sm-7" type="number" name="phoneNumber" value={phoneNumber} onChange={(e) => changeHandler(e)} />
                                        </div>
                                        <div className="input-group-text col-sm-10">
                                            <label className="col-sm-5">Mail</label>
                                            <input className="col-sm-7" type="email" name="email" value={email} onChange={(e) => changeHandler(e)} />
                                        </div>
                                        <button className="btn btn-success col-sm-3 offset-sm-3 mt-2" onClick={(e) => updateWaiter(e)}>Save</button>
                                        <button className="btn btn-danger col-sm-3 offset-sm-1 mt-2" onClick={(e) => cancel(e)}>Cancel</button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            }
        </div>
    )
}
export default EditWaiter;