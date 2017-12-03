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
import { HistoryService } from './history.service';
import { StoryComponent } from './story/story.component';
import { TaskComponent } from './task/task.component';
import { TaskService } from './task/task.service';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpModule } from '@angular/http';
import { AuthGuard } from './auth-guard.service';
import { ChartsModule } from 'ng2-charts';
import { BurndownComponent } from './burndown/burndown.component';
import { DatePipe } from '@angular/common';
import { ValidationService } from './validation.service';
import { BoardNotFoundComponent } from './board-not-found/board-not-found.component';
import { NotAuthorizedComponent } from './not-authorized/not-authorized.component';

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
    TaskComponent,
    BurndownComponent,
    BoardNotFoundComponent,
    NotAuthorizedComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BsDropdownModule.forRoot(),
    NgbModule.forRoot(),
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
    HistoryService,
    ValidationService,
    AuthGuard,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
