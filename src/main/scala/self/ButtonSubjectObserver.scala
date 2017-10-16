package button.subject.observer

import subject.observer.{ SubjectObserver }

case class Button(label: String) {
  def click(): Unit = println(s"click: ${label}")
}

object ButtonSubjectObserver extends SubjectObserver {
  type S = ObservableButton
  type O = ButtonObserver

  class ObservableButton(label: String) extends Button(label) with Subject {
    override def click() = {
      super.click()
      notifyObservers()
    }
  }

  trait ButtonObserver extends Observer {
    def receiveUpdate(button: ObservableButton)
  }
}
