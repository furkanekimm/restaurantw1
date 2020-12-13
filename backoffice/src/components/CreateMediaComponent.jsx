import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import MediaService from "../services/MediaService";

class CreateMediaComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            selectedFile:'',
            imageList:[]
        }
        this.onImageChange = this.onImageChange.bind(this);
        this.onFileUpload = this.onFileUpload.bind(this);
    }
    onFileUpload=()=>{
        if(!this.state.selectedFile){
            alert("choose a file...!");
            return;
        }
        const data = new FormData();
        data.append('multipartFile',this.state.selectedFile)
        data.append('imageName',this.state.selectedFile.name)
        MediaService.addMedia(data).then(res=>{
            window.location.reload();
        })

        console.log(this.state.selectedFile);
    }

    onImageChange=(e)=>{
       console.warn("event : ",e)
        if(e.target.files && e.target.files[0]){
            this.setState({selectedFile:e.target.files[0]})
        }
    }

    componentDidMount() {
        MediaService.getAllMedia().then((res)=>{
            this.setState({imageList:res.data})
        });
    }

    render() {
        return (
            <div>
                <div className="container">
                    <HeaderComponent/>
                    <div className="col-sm-12 mt-4">
                        <div className="row ">
                            <div className="card col-sm-4 " style={{backgroundColor:"#a6dced"}}>
                                <div className="card-header bg-white text-center" style={{borderRadius:"10px"}}>Add Media File</div>
                                <div className="card-body">
                                    <input type = "file" name="file" className="ml-5" style={{paddingTop: 20}} onChange={(e) => this.onImageChange(e)}/>

                                </div>
                                <div className="card-body ml-5" style={{height:"30rem",overflow:"auto"}}>
                                    <button className="btn btn-success" style={{borderRadius:"5px",marginTop:"23rem"}} onClick={()=>this.onFileUpload()}> Upload Media File</button>
                                </div>
                            </div>
                            <div className="card col-sm-8" style={{height:"39rem",overflow:"auto"}} >
                                <div className="card-header text-center">Media Files</div>
                                <div className="row ">

                                        {
                                            this.state.imageList.map(
                                                image=>
                                                    <div className="card-body col-sm-3 ml-4">
                                                    <img src={'data:image/png;base64,' +image.fileContent} width="150rem" height="130rem" style ={{margin: 10}}/>
                                                    </div>
                                            )
                                        }

                                </div>
                            </div>
                        </div>

                        </div>
                </div>
            </div>
        );
    }
}

export default CreateMediaComponent;