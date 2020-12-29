import React, {Component, useContext, useState,useRef} from 'react';
import {useHistory} from 'react-router-dom';
import {Context} from '../contexts/Context';

const HeaderComponent=(props)=>{
    const refTurkish = useRef();
    const refEnglish = useRef();
    const{lang,setLang} = useContext(Context);
    const turkeyFlag = "https://upload.wikimedia.org/wikipedia/commons/b/b4/Flag_of_Turkey.svg";
    const englandFlag = "https://i4.hurimg.com/i/hurriyet/75/750x422/55ea65fdf018fbb8f87d5486.jpg";
    const history = useHistory();
    const logout=(e)=>{
        localStorage.removeItem("username");
        localStorage.removeItem("password");
        history.push('/');
    }
    const goCategories=(e)=>{
        history.push('/categories');
    }
    const goProducts=(e)=>{
        history.push('/products');
    }

    const goWaiters=(e)=>{
        history.push('/waiters');
    }

    const goUsers=(e)=>{
        history.push('/users');
    }

    const goRoles=(e)=>{
        history.push('/roles');
    }

    const goMedias=(e)=>{
        history.push('/createmedia');
    }

    const goReports=(e)=>{
        history.push('/reports');
    }

    const goInfos=(e)=>{
        history.push('/infos');
    }

    const goTables=(e)=>{
        history.push('/tables');
    }
    const goCustomers=(e)=>{
        history.push('/customers');
    }
    
    const changeLanguage=(e)=>{
        console.log(refTurkish)
        setLang({...lang,lang:'tr'});
    }

    return (
        /*<div>
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div><a href="/product" className="navbar-brand">Products</a></div>
                    <div><a href="/listuser" className="navbar-brand">Users</a></div>
                    <form className="form-inline my-2 my-lg-0">
                            <button className="btn btn-outline-success my-2 my-sm-0" onClick={this.logout}>Logout</button>
                    </form>

                </nav>
            </header>
        </div>*/
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <button className="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span className="navbar-toggler-icon">asdsad</span>
            </button>
            <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a className="navbar-brand" href="#">Admin Control</a>
                <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li className="nav-item active">
                        <a className="nav-link" href="#" onClick={(e)=>goProducts(e)} >Products <span className="sr-only">(current)</span></a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={(e)=>goUsers(e)}>Users</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={(e)=>goRoles(e)}>Roles</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link"  onClick={(e)=>goCategories(e)}>Categories</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" onClick={(e)=>goWaiters(e)}>Waiters</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={(e)=>goReports(e)}>Reports</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={(e)=>goMedias(e)} >Medias</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={(e)=>goInfos(e)}>Info</a>
                    </li>

                    <li className="nav-item">
                        <a className="nav-link " href="#" onClick={(e)=>goTables(e)}>TablePlaces</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link " href="#" onClick={(e)=>goCustomers(e)}>Customers</a>
                    </li>
                </ul>
                <form className="form-inline my-2 my-lg-0">
                <a href="#"  onClick={(e)=>changeLanguage(e)}><img ref={refTurkish} src={turkeyFlag} width="45rem" height="35rem" style={{marginRight:"5px"}}/></a>
                <a href="#"  onClick={()=>setLang({...lang,lang:'en'})}><img ref={refEnglish} src={englandFlag} width="45rem" height="35rem" style={{marginRight:"5px"}}/></a>
                        <button className="btn btn-outline-danger my-2 my-sm-0" type="submit" onClick={()=>logout()}>Logout</button>
                </form>
            </div>
        </nav>
    );
}
export default HeaderComponent;
