import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { AboutmeFooterComponent } from './aboutme-footer.component';



@NgModule({
  declarations: [
    AboutmeFooterComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule
  ],
  exports: [
    AboutmeFooterComponent
  ]
})
export class AboutmeFooterModule { }
