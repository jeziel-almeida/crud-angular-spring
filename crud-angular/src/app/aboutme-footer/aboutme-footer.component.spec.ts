import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutmeFooterComponent } from './aboutme-footer.component';

describe('AboutmeFooterComponent', () => {
  let component: AboutmeFooterComponent;
  let fixture: ComponentFixture<AboutmeFooterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AboutmeFooterComponent]
    });
    fixture = TestBed.createComponent(AboutmeFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
