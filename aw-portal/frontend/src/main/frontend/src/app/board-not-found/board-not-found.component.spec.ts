import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardNotFoundComponent } from './board-not-found.component';

describe('BoardNotFoundComponent', () => {
  let component: BoardNotFoundComponent;
  let fixture: ComponentFixture<BoardNotFoundComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoardNotFoundComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardNotFoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
