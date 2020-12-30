import React, { useContext, useEffect, useState,useRef } from 'react';
import { useFormik } from 'formik';
import {useHistory} from 'react-router-dom';
import {Context} from '../../contexts/Context';
import MediaService from "../../services/MediaService";
const validate = values => {

    const errors = {};
    if (!values.name) {
        errors.name = 'Required';
    } else if (values.name.length > 5) {
        errors.name = 'Must be 10 characters or less';
    }

    if (!values.lastName) {
        errors.lastName = 'Required';
    } else if (values.lastName.length > 5) {
        errors.lastName = 'Must be 20 characters or less';
    }

    if (!values.address) {
        errors.address = 'Required';
    } else if (values.address.length > 5) {
        errors.address = 'Must be 20 characters or less';
    }

    if (!values.phone) {
        errors.phone = 'Required';
    } else if (values.phone.length > 5) {
        errors.phone = 'Must be 10 characters or less';
    }

    return errors;
}

const AddCustomerWithFormik = (props) => {
    const refImage = useRef();
    const{users,authorizeControl} = useContext(Context);
    const[medias,setMedias] = useState();
    const history = useHistory();
    const formik = useFormik({
        initialValues: {
            name: '',
            lastName: '',
            address: '',
            phone: '',
            media: '',
        },
        validate,
        onSubmit: values => {
            alert(JSON.stringify(values, null, 2));
        },
    });
    const showImage = async (e) => {
        const refId= refImage.current.value;
        e.preventDefault();
        await formik.values.media(medias.filter(media => media.id == refId)[0])
    }

    async function getMedias(){
        const res = await MediaService.getAllMedia(users);
        if(res.status===200){
            setMedias(res.data);
            formik.values.media=res.data[0]
        }
    }
    useEffect(()=>{
        getMedias();
    },[])

    return (
        <form onSubmit={formik.handleSubmit}>
            <label htmlFor="name">First Name</label>
            <input
                id="name"
                name="name"
                type="text"
                onChange={formik.handleChange}
                value={formik.values.name}
            />
            {formik.errors.name ? <div>{formik.errors.name}</div> : null}
            <label htmlFor="lastName">Last Name</label>
            <input
                id="lastName"
                name="lastName"
                type="text"
                onChange={formik.handleChange}
                value={formik.values.lastName}
            />
            {formik.errors.lastName ? <div>{formik.errors.lastName}</div> : null}
            <label htmlFor="address">Address</label>
            <input
                id="address"
                name="address"
                type="text"
                onChange={formik.handleChange}
                value={formik.values.address}
            />
            {formik.errors.phone ? <div>{formik.errors.phone}</div> : null}
            <label htmlFor="phone">Phone</label>
            <input
                id="phone"
                name="phone"
                type="number"
                onChange={formik.handleChange}
                value={formik.values.phone}
            />
            {formik.errors.phone ? <div>{formik.errors.phone}</div> : null}
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
                                                <img src={'data:image/png;base64,' + formik.values.media.fileContent}
                                                    width="45rem" height="39rem"
                                                />
                                            </div>
                                        </div>
                                    </div>
                                </div>
            <button type="submit">Submit</button>
        </form>
    );
}
export default AddCustomerWithFormik;