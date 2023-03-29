import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CameraComponent } from './components/camera.component';
import { UploadComponent } from './components/upload.component';
import { CameraService } from './service/camera.service';
import { HttpClientModule } from '@angular/common/http';
import { WebcamModule} from 'ngx-webcam';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    CameraComponent,
    UploadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    WebcamModule,
    ReactiveFormsModule
  ],
  providers: [CameraService],
  bootstrap: [AppComponent]
})
export class AppModule { }
