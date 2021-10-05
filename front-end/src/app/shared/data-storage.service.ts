import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Post } from "./post.model";
import { map, take, tap } from 'rxjs/operators';

@Injectable({
    providedIn: "root"
})
export class DataStorageService{
    constructor(private http: HttpClient,
                ){}


    fetchRecipes(){
        return this.http.get<Post[]>('http://localhost:8080/api/posts');
    }

}