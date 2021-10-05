import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DataStorageService } from '../shared/data-storage.service';
import { Post } from '../shared/post.model';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit, OnDestroy {
  posts: Post[];
  subscription: Subscription;

  constructor(private dataStorage: DataStorageService) { }
  

  ngOnInit(): void {
    this.subscription = this.dataStorage.fetchRecipes().subscribe((posts: Post[]) =>{
      this.posts = posts;
    })
    
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
