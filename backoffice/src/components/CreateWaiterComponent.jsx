import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import WaiterService from "../services/WaiterService";
import MediaService from "../services/MediaService";

class CreateWaiterComponent extends Component {
    constructor(props) {
        super(props)
        this.state={
            waiterName:'',
            waiterLastName:'',
            phoneNumber:'',
            email:'',
            media: [],
            selectedImage: [],
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changePhoneNumberHandler = this.changePhoneNumberHandler.bind(this);
        this.changeMailHandler = this.changeMailHandler.bind(this);
        this.addWaiter = this.addWaiter.bind(this);
        this.showImage = this.showImage.bind(this);
    }
    showImage(id) {
        this.setState({selectedImage: this.state.media.filter(media => media.id == document.getElementById('option').value)})
    }

    addWaiter = (e) =>{
        e.preventDefault();
        let waiter={
            waiterName: this.state.waiterName,
            waiterLastName: this.state.waiterLastName,
            phoneNumber: this.state.phoneNumber,
            email : this.state.email,
            media:this.state.selectedImage[0]
        };
        if(waiter.waiterName ==='' || waiter.waiterLastName ==='' || waiter.phoneNumber ==='' || waiter.email ===''){
            window.location.reload();
        }else{
            WaiterService.addWaiter(waiter).then(res=>{
                if(res.data===true){
                    console.log("Başarılı ekleme");
                    this.props.history.push("/listwaiters");
                }
            });
        }

    }
    componentDidMount() {
        MediaService.getAllMedia().then((res) => {
            this.setState({media: res.data})
        })
        /*   this.setState({selectedImage:this.state.media[0]})*/
    }
    changeNameHandler = (e) =>{
        this.setState({waiterName:e.target.value})
    }
    changeLastNameHandler = (e) =>{
        this.setState({waiterLastName:e.target.value})
    }
    changePhoneNumberHandler = (e) =>{
        this.setState({phoneNumber:e.target.value})
    }
    changeMailHandler = (e) =>{
        this.setState({email:e.target.value})
    }
    cancel = (e)=>{
        this.props.history.push('listwaiters')
    }

    render() {
        return (
            <div>
                <div className="container">
                    <HeaderComponent/>
                <div className="card  col-sm-6 offset-md-3 border-dark mt-4">
                    <div className="card-body">
                        <h4 className="card-header text-center bg-transparent">Add Waiter</h4>
                        <form>
                            <div className="form-group">
                                <div className="form-group ">
                                    <label> Waiter Image </label>
                                    <div className="row">
                                        <div className="col-md-6">
                                            <select onChange={() => this.showImage()}
                                                    className="form-control" id="option">
                                                {
                                                    this.state.media.map(
                                                        media =>
                                                            <option key={media.id}
                                                                    value={media.id}>{media.name} </option>
                                                    )
                                                }
                                            </select>
                                        </div>
                                        <div className="col-md-4 offset-md-2">
                                            {
                                                this.state.selectedImage.map(
                                                    image =>
                                                        <div className="">
                                                            <img src={'data:image/png;base64,' + image.fileContent}
                                                                 width="45rem" height="39rem"
                                                            />
                                                        </div>
                                                )
                                            }
                                        </div>
                                    </div>
                                </div>
                                <div className="input-group col-sm-12 mt-2">
                                    <div className="input-group-text col-sm-10">
                                        <label className="col-sm-5">Name</label>
                                        <input className="col-sm-7" type="text" onChange={this.changeNameHandler} />
                                    </div>
                                    <div className="input-group-text col-sm-10">
                                        <label className="col-sm-5">LastName</label>
                                        <input className="col-sm-7" type="text" onChange={this.changeLastNameHandler} />
                                    </div>
                                    <div className="input-group-text col-sm-10">
                                        <label className="col-sm-5">PhoneNumber</label>
                                        <input className="col-sm-7" type="number" onChange={this.changePhoneNumberHandler} />
                                    </div>
                                    <div className="input-group-text col-sm-10">
                                        <label className="col-sm-5">Mail</label>
                                        <input className="col-sm-7" type="email" onChange={this.changeMailHandler} />
                                    </div>
                                    <button className="btn btn-success col-sm-3 offset-sm-3 mt-2" onClick={this.addWaiter}>Save</button>
                                    <button className="btn btn-danger col-sm-3 offset-sm-1 mt-2" onClick={this.cancel}>Cancel</button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
                </div>
            </div>
        );
    }
}

export default CreateWaiterComponent;