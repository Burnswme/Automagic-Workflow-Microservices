import { BackendService } from './backend.service';
import { StoryService } from './story/story.service';
import { BoardComponent } from './board/board.component';
import { BoardService } from './board/board.service';
import { DataService } from './data.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AlertModule } from 'ngx-bootstrap';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './/app-routing.module';
import { ModalComponent } from './modal.component';
import { SwimlaneComponent } from './swimlane/swimlane.component';
import { SwimlaneService } from './swimlane/swimlane.service';
import { StoryComponent } from './story/story.component';
import { TaskComponent } from './task/task.component';
import { TaskService } from './task/task.service';
import { HttpModule } from '@angular/http';
import { AuthGuard } from './auth-guard.service';
import { ChartsModule } from 'ng2-charts';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    HomeComponent,
    ModalComponent,
    BoardComponent,
    SwimlaneComponent,
    StoryComponent,
    TaskComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BsDropdownModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    HttpModule,
    ChartsModule
  ],
  providers: [
    BackendService,
    DataService,
    BoardService,
    SwimlaneService,
    StoryService,
    TaskService,
    BackendService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
