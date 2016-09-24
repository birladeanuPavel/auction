/**
 * Created by pavel on 8/20/16.
 */
import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { AppComponent } from './app.component';
@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        AppComponent
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
